package co.com.msservielectrogas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class Schedules {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSchedule;

	@Column(name = "date")
	private Date date;

	@Column(name = "hour")
	private String hour;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
    private Users users ;

	@OneToOne
    @JoinColumn(name = "FK_ORDERS", updatable = true, nullable = false)
    private Order orders;

}
