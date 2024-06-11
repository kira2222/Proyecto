package co.com.msservielectrogas.dto;

import java.util.Date;

public class ServiceReportDTO {
    private String technicianName;
    private String clientName;
    private String observations;
    private long totalCharged;
    private Date createdAt;

    // Constructor
    public ServiceReportDTO(String technicianName, String clientName, String observations, long totalCharged, Date createdAt) {
        System.out.println("Creating ServiceReportDTO: technicianName=" + technicianName + ", clientName=" + clientName + ", observations=" + observations + ", totalCharged=" + totalCharged + ", createdAt=" + createdAt);
        this.technicianName = technicianName;
        this.clientName = clientName;
        this.observations = observations;
        this.totalCharged = totalCharged;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public long getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(long totalCharged) {
        this.totalCharged = totalCharged;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
