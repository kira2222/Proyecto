package co.com.msservielectrogas.dto;

import java.util.Date;
import java.time.LocalDateTime;

public class WarrantyWithOrderServiceDTO {

    private Long id;
    private String reason;
    private Date startDate;
    private Date endDate;
    private Long orderServiceId;
    private String orderServiceObservations;
    private LocalDateTime orderServiceDate;

    public WarrantyWithOrderServiceDTO(Long id, String reason, Date startDate, Date endDate, Long orderServiceId, String orderServiceObservations, LocalDateTime orderServiceDate) {
        this.id = id;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderServiceId = orderServiceId;
        this.orderServiceObservations = orderServiceObservations;
        this.orderServiceDate = orderServiceDate;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(Long orderServiceId) {
        this.orderServiceId = orderServiceId;
    }

    public String getOrderServiceObservations() {
        return orderServiceObservations;
    }

    public void setOrderServiceObservations(String orderServiceObservations) {
        this.orderServiceObservations = orderServiceObservations;
    }

    public LocalDateTime getOrderServiceDate() {
        return orderServiceDate;
    }

    public void setOrderServiceDate(LocalDateTime orderServiceDate) {
        this.orderServiceDate = orderServiceDate;
    }
}
