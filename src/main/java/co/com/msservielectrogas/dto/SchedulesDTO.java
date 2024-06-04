package co.com.msservielectrogas.dto;


import lombok.Data;

@Data
public class SchedulesDTO {

	private Integer id;
	private String date;
	private String hour;
	private Long idOrder;
	private Integer idUser;
}
