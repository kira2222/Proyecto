package co.com.msservielectrogas.dto;

import java.util.Date;
import java.util.List;

import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.enums.EStatus;
import lombok.Data;

@Data
public class OrderDTO {

	private Long id;
	private List<OrderServiceDTO> orderServices;
	private String observations;
	private EStatus statusName;
	private Integer status;
	private Long totalCharged;
    private String clientName;
    private Long clientId;
    
    private Date createdAt;
    private Date updatedAt;
    
    public OrderDTO() {
    }
    
    public OrderDTO(Long id, String observations, EStatus statusName, Integer status, Long totalCharged, String clientName, Long clientId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.observations = observations;
        this.statusName = statusName;
        this.status = status;
        this.totalCharged = totalCharged;
        this.clientName = clientName;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public OrderDTO(Long id, List<OrderServiceDTO> orderServices, String observations,Integer status, Long totalCharged, Long clientId) {
        this.id = id;
        this.orderServices = orderServices;
        this.observations = observations;
        this.status = status;
        this.totalCharged = totalCharged;
        this.clientId = clientId;
    }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderServiceDTO> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(List<OrderServiceDTO> orderServices) {
        this.orderServices = orderServices;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public EStatus getStatusName() {
        return statusName;
    }

    public void setStatusName(EStatus statusName) {
        this.statusName = statusName;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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
}
