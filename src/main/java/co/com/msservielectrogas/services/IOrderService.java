package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.Order;
import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.ClientDTO;
import co.com.msservielectrogas.dto.CreateOrderDTO;
import co.com.msservielectrogas.dto.UserDTO;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.repository.IOrderServiceRepository;
import co.com.msservielectrogas.repository.ISchedulesRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.specification.OrderSpecifications;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOrderService {

    @Autowired
    private IOrderRepository orderRepository;
    
    @Autowired
    private IUsersRepository userRepository;
    
    @Autowired
    private ISchedulesRepository scheduleRepository;
    
    @Autowired
    private IClientService clientService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IOrderServiceService orderServiceService;
    
    @Autowired
    private IOrderServiceRepository orderServiceRepository;
    
    public Page<OrderDTO> getAllOrders(String observations, Pageable pageable) {
        Specification<Order> spec = Specification.where(OrderSpecifications.hasObservations(observations));
        return orderRepository.findAll(spec, pageable)
                .map(this::convertToDTOWithOrderServices);
    }
    
    public ApiResponseDTO<OrderDTO> createOrder(CreateOrderDTO createOrderDTO) {
    	
    	ClientDTO clientDTO = clientService.getClientById(createOrderDTO.getClientId());
    	
        if (clientDTO == null) {
            return new ApiResponseDTO<>("El Client no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }

        UserDTO userDTO = userService.getUserById(createOrderDTO.getTechnicianId());
        
        if (userDTO == null) {
            return new ApiResponseDTO<>("El Técnico no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }
        
        Clients client = new Clients();
        client.setId(clientDTO.getId());
        client.setDocument(clientDTO.getDocument());
        client.setNames(clientDTO.getNames());
        client.setPhone(clientDTO.getPhone());
        client.setPhone1(clientDTO.getPhone1());
        client.setPhone2(clientDTO.getPhone2());
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        client.setNameOfApplicant(clientDTO.getNameOfApplicant());
        client.setNumberOrderVendor(clientDTO.getNumberOrderVendor());
        client.setType(clientDTO.getType());

        Order order = new Order();
        order.setObservations(createOrderDTO.getObservations());
        order.setStatus(createOrderDTO.getStatus());
        order.setClient(client);
        order.setCreatedAt(new Date());
        order.setTotalCharged(createOrderDTO.getTotalCharged());
        
        order = orderRepository.save(order);
        
        // Create Order Services assigned to the Order

        List<OrderServiceDTO> createdOrderServiceDTOs = new ArrayList<>();
        for (OrderServiceDTO orderServiceDTO : createOrderDTO.getOrderServices()) {
            OrderServiceDTO createdOrderServiceDTO = orderServiceService.createOrderService(orderServiceDTO, order.getId(), createOrderDTO.getTechnicianId());
            createdOrderServiceDTOs.add(createdOrderServiceDTO);
        }
        
        // Create Schedule assigned to the Order

        Users user = new Users();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        Schedules schedule = new Schedules();
        schedule.setDate(createOrderDTO.getScheduleDate());
        schedule.setHour(createOrderDTO.getScheduleTime());
        schedule.setOrders(orderRepository.findById(order.getId()));
        schedule.setUsers(userRepository.findById(user.getId()).orElse(null));
        
        scheduleRepository.save(schedule);
        
        OrderDTO orderDTO = convertToDTO(order);
        orderDTO.setOrderServices(createdOrderServiceDTOs);
        
        return new ApiResponseDTO<>("Order created successfully", HttpStatus.CREATED.value(), orderDTO);
    }
    
    public OrderDTO getOrderById(Long id) {
    	Order order = orderRepository.findById(id);
    	
        if (order == null) {
            return null;
        }
        return convertToDTO(order);
    }
    
    private OrderDTO convertToDTOWithOrderServices(Order order) {
        OrderDTO orderDTO = convertToDTO(order);
        List<OrderServiceDTO> orderServiceDTOs = orderServiceRepository.findByOrderId(order.getId()).stream()
                .map(this::convertToOrderServiceDTO)
                .collect(Collectors.toList());
        orderDTO.setOrderServices(orderServiceDTOs);
        return orderDTO;
    }
    
    private OrderDTO convertToDTO(Order order) {
        EStatus statusName = EStatus.values()[order.getStatus().intValue()];
        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setObservations(order.getObservations());
        orderDTO.setStatusName(statusName);
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalCharged(order.getTotalCharged());
        orderDTO.setClientName(order.getClient().getNames());
        orderDTO.setClientId(order.getClient().getId());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        return orderDTO;
    }

    private OrderServiceDTO convertToOrderServiceDTO(OrderService orderService) {
        EPriority priorityName = EPriority.values()[orderService.getPriority().intValue()];
        EStatus statusName = EStatus.values()[orderService.getStatus().intValue()];

        OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
        orderServiceDTO.setId(orderService.getId());
        orderServiceDTO.setOrderId(orderService.getOrder().getId());
        orderServiceDTO.setService(orderService.getService());
        orderServiceDTO.setServiceId(orderService.getService().getId());
        orderServiceDTO.setObservations(orderService.getObservations());
        orderServiceDTO.setOrderServiceDate(orderService.getOrderServiceDate());
        orderServiceDTO.setDuration(orderService.getDuration());
        orderServiceDTO.setPriorityName(priorityName);
        orderServiceDTO.setPriority(orderService.getPriority());
        orderServiceDTO.setStatusName(statusName);
        orderServiceDTO.setStatus(orderService.getStatus());
        orderServiceDTO.setCreatedAt(orderService.getCreatedAt());
        orderServiceDTO.setTechnicianName(orderService.getTechnician().getName());
        orderServiceDTO.setTechnicianId(orderService.getTechnician().getId());
        return orderServiceDTO;
    }
}