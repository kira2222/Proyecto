package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.Order;
import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.CreateScheduleDTO;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.dto.SchedulesDTO;
import co.com.msservielectrogas.dto.UserDTO;
import co.com.msservielectrogas.repository.ISchedulesRepository;
import co.com.msservielectrogas.repository.IClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ISchedulesService {
	
    @Autowired
    private ISchedulesRepository scheduleRepository;
	
    @Autowired
    private IClientRepository clientRepository;
    
    @Autowired
    private IOrderService orderService;
    
    @Autowired
    private IUserService userService;
    
    public ApiResponseDTO<SchedulesDTO> createSchedule(CreateScheduleDTO createScheduleDTO) {
        if (createScheduleDTO.getOrderId() == null) {
            return new ApiResponseDTO<>("La Orden es requerida", HttpStatus.BAD_REQUEST.value(), null);
        }

        OrderDTO orderDTO = orderService.getOrderById(createScheduleDTO.getOrderId());
        
        if (orderDTO == null) {
            return new ApiResponseDTO<>("La Orden no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }
        
        if (createScheduleDTO.getTechnicianId() == null) {
            return new ApiResponseDTO<>("El Técnico es requerido", HttpStatus.BAD_REQUEST.value(), null);
        }

        UserDTO userDTO = userService.getUserById(createScheduleDTO.getTechnicianId());
        
        if (userDTO == null) {
            return new ApiResponseDTO<>("El Técnico no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }
        
        Order order = new Order();
        order.setObservations(orderDTO.getObservations());
        order.setStatus(orderDTO.getStatus());
        order.setClient(clientRepository.findById(orderDTO.getClientId()));
        order.setCreatedAt(orderDTO.getCreatedAt());

        Users user = new Users();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        
        Schedules schedule = new Schedules();
    	
        schedule.setDate(createScheduleDTO.getDate());    		
        schedule.setHour(createScheduleDTO.getHour());
        schedule.setUsers(user);
        schedule.setOrders(order);
        
        schedule = scheduleRepository.save(schedule);

        SchedulesDTO scheduleDTO = convertToDTO(schedule);
        
        return new ApiResponseDTO<>("Order created successfully", HttpStatus.CREATED.value(), scheduleDTO);
    }
    
    private SchedulesDTO convertToDTO(Schedules schedule) {
        return new SchedulesDTO(
        		schedule.getIdSchedule(),
        		schedule.getDate(),
        		schedule.getHour(),
        		schedule.getOrders().getId(),
        		schedule.getUsers().getId()
        );
    }
}