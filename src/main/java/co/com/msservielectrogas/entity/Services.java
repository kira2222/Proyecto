package co.com.msservielectrogas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import co.com.msservielectrogas.enums.EServicesType;
import lombok.Data;

@Data
@Entity
@Table(name = "services")
public class Services {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false)
    private Integer servicesType;
	
	@Column(nullable = false)
    private String servicesDescription;
	
	@Column(nullable = false)
    private Long price;
	
}
