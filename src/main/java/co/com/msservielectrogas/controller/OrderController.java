package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.CreateOrderDTO;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<OrderDTO>>> getAllOrders(
            @RequestParam(required = false) String observations,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(getSortOrders(sort)));

        Page<OrderDTO> serviceActivities = orderService.getAllOrders(observations, pageable);
        ApiResponseDTO<Page<OrderDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), serviceActivities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ApiResponseDTO<OrderDTO>> createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        ApiResponseDTO<OrderDTO> response = orderService.createOrder(createOrderDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    private List<Sort.Order> getSortOrders(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // sort order like: "field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            // default sort order
            orders.add(new Sort.Order(Sort.Direction.ASC, sort[0]));
        }

        return orders;
    }
}
