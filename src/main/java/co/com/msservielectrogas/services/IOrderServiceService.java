package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.repository.IOrderServiceRepository;
import co.com.msservielectrogas.repository.IServicesRepository;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IOrderServiceService {

    @Autowired
    private IOrderServiceRepository orderServiceRepository;

    @Autowired
    private IOrderRepository orderRepository;
    
    @Autowired
    private IServicesRepository serviceRepository;
    
    @Autowired
    private IUsersRepository usersRepository;
    
    public OrderServiceDTO getOrderServiceById(Long id) {
        Optional<OrderService> optionalOrderService = orderServiceRepository.findById(id);

        if (!optionalOrderService.isPresent()) {
            return null;
        }

        return convertToDTO(optionalOrderService.get());
    }
    
    public OrderServiceDTO createOrderService(OrderServiceDTO createOrderServiceDTO, Long orderId, Integer technicianId) {
        OrderService orderService = new OrderService();
        
        orderService.setOrder(orderRepository.findById(orderId).orElse(null));
        orderService.setService(serviceRepository.findById(createOrderServiceDTO.getServiceId()).orElse(null));
        orderService.setObservations(createOrderServiceDTO.getObservations());
        orderService.setOrderServiceDate(createOrderServiceDTO.getOrderServiceDate());
        orderService.setDuration(createOrderServiceDTO.getDuration());
        orderService.setPriority(createOrderServiceDTO.getPriority());
        orderService.setStatus(createOrderServiceDTO.getStatus());
        orderService.setCreatedAt(LocalDateTime.now());
        orderService.setTechnician(usersRepository.findById(technicianId).orElse(null));

        OrderService savedOrderService = orderServiceRepository.save(orderService);

        return convertToDTO(savedOrderService);
    }

    private OrderServiceDTO convertToDTO(OrderService orderService) {
        EPriority priorityName = EPriority.values()[orderService.getPriority().intValue()];
        EStatus statusName = EStatus.values()[orderService.getStatus().intValue()];

        return new OrderServiceDTO(
                orderService.getId(),
                orderService.getOrder().getId(),
                orderService.getService(),
                orderService.getService().getId(),
                orderService.getObservations(),
                orderService.getOrderServiceDate(),
                orderService.getDuration(),
                priorityName,
                orderService.getPriority(),
                statusName,
                orderService.getStatus(),
                orderService.getCreatedAt(),
                orderService.getTechnician().getName(),
                orderService.getTechnician().getId()
        );
    }
}
