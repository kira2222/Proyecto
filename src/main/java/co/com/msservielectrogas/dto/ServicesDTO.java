package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.EServicesType;
import lombok.Data;

@Data
public class ServicesDTO {

	private Integer id;
	private EServicesType servicesType;
	private String servicesDescription;
	private Long price;
	
}
