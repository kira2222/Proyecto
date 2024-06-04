package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.ERoles;
import lombok.Data;

@Data
public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private ERoles typeName;
	private Integer type;
}
