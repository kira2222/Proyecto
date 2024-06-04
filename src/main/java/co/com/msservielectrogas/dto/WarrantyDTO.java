package co.com.msservielectrogas.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WarrantyDTO {
    private Long id;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long orderServiceId;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(Long orderServiceId) {
        this.orderServiceId = orderServiceId;
    }
}
