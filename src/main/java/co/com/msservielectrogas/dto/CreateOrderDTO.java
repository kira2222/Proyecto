package co.com.msservielectrogas.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    private String observations;
	private List<OrderServiceDTO> orderServices;
	private Integer status;
    private Long clientId;
}