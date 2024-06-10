package co.com.msservielectrogas.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.com.msservielectrogas.enums.EStatus;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Transient
    private List<OrderService> orderServices;
	
	@Column(nullable = true, length = 255)
    private String observations;
	
	@Column(name = "status") 
    private Integer status;
	
	@Column(nullable = true)
    private Long totalCharged;
	
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Clients client;
	
    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(Long id, String observations,Integer status, Long totalCharged, Date createdAt, Date updatedAt, Clients client) {
        this.id = id;
        this.observations = observations;
        this.status = status;
        this.totalCharged = totalCharged;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderService> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(List<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(Long totalCharged) {
        this.totalCharged = totalCharged;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Order orElse(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }
}
