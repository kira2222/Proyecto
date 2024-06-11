package co.com.msservielectrogas.dto;

public class TechnicianSettlementDTO {
    private String document;
    private String name;
    private long totalCharged;
    private String month;
    public TechnicianSettlementDTO(String document, String name, long totalCharged, String month) {
        this.document = document;
        this.name = name;
        this.totalCharged = totalCharged;
        this.month = month;
    }

    // Getters y Setters
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(long totalCharged) {
        this.totalCharged = totalCharged;
    }

    public String getmonth() {
        return month;
    }

    public void setmonth(String month) {
        this.month = month;
    }
}
