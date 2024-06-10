package co.com.msservielectrogas.dto;

public class StatisticsDTO {
    private String estado;
    private Long cantidad;

    public StatisticsDTO(String estado, Long cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }

    // Getters and setters
}
