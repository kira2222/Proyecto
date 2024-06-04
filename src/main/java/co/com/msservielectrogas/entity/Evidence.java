package co.com.msservielectrogas.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidences")
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photoUrl;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_service_id")
    private OrderService orderService;

    public Evidence() {
    }

    public Evidence(Long id, String photoUrl, LocalDateTime createdAt, OrderService orderService) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
        this.orderService = orderService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
