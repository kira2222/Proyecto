package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.entity.Order;
import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.entity.Services;
import co.com.msservielectrogas.entity.Users;

public class OrderServiceDetailsDTO {
    private OrderService orderService;
    private Order order;
    private Services service;
    private Users technician;

    public OrderServiceDetailsDTO(OrderService orderService, Order order, Services service, Users technician) {
        this.orderService = orderService;
        this.order = order;
        this.service = service;
        this.technician = technician;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Users getTechnician() {
        return technician;
    }

    public void setTechnician(Users technician) {
        this.technician = technician;
    }
}
