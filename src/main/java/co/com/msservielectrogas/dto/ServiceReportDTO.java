package co.com.msservielectrogas.dto;

import java.time.LocalDateTime;

public class ServiceReportDTO {
    private String document;
    private String names;
    private String nameOfApplicant;
    private String servicesDescription;
    private long totalCharged;
    private LocalDateTime orderServiceDate;

    // Constructor
    public ServiceReportDTO(String document, String names, String nameOfApplicant, String servicesDescription, long totalCharged, LocalDateTime orderServiceDate) {
        this.document = document;
        this.names = names;
        this.nameOfApplicant = nameOfApplicant;
        this.servicesDescription = servicesDescription;
        this.totalCharged = totalCharged;
        this.orderServiceDate = orderServiceDate;
    }

    // Getters y setters
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getNameOfApplicant() {
        return nameOfApplicant;
    }

    public void setNameOfApplicant(String nameOfApplicant) {
        this.nameOfApplicant = nameOfApplicant;
    }

    public String getServicesDescription() {
        return servicesDescription;
    }

    public void setServicesDescription(String servicesDescription) {
        this.servicesDescription = servicesDescription;
    }

    public long getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(long totalCharged) {
        this.totalCharged = totalCharged;
    }

    public LocalDateTime getOrderServiceDate() {
        return orderServiceDate;
    }

    public void setOrderServiceDate(LocalDateTime orderServiceDate) {
        this.orderServiceDate = orderServiceDate;
    }
}
