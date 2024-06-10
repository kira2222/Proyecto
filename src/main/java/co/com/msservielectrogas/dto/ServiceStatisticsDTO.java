package co.com.msservielectrogas.dto;

import java.time.LocalDateTime;

public class ServiceStatisticsDTO {
    private LocalDateTime mes;
    private String tipoServicio;
    private Long cantidad;

    public ServiceStatisticsDTO(LocalDateTime mes, String tipoServicio, Long cantidad) {
        this.mes = mes;
        this.tipoServicio = tipoServicio;
        this.cantidad = cantidad;
    }

    // Getters and setters
}
