package co.com.msservielectrogas.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.msservielectrogas.dto.ClientDTO;
import co.com.msservielectrogas.dto.DataDTO;
import co.com.msservielectrogas.dto.ResponseDTO;
import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.Order;
import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.repository.IClientRepository;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.util.ConvertsDtosInEntitys;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

	@Autowired
	private IClientRepository clientRepository;

	@Autowired
	private IOrderRepository ordersRepository;

	@Autowired
	private ConvertsDtosInEntitys dtosInEntitys;

	private ResponseDTO response;

	/*
	@PostMapping(value = "/registerAll")
	public ResponseEntity<?> createOrderWithClient(@RequestBody DataDTO dto) {
		response = new ResponseDTO();
		Order orders = null;
		Clients clients = null;
		
		
		Clients clientValidation = clientRepository.findByPhoneOrDocumentOrAddress(dto.getClient().getPhone(),
				dto.getClient().getDocument(), dto.getClient().getAddress());
		if (Objects.nonNull(clientValidation)) {
			ClientDTO clientDTO = dtosInEntitys.convertEntityInDtoClient(clientValidation);
			clientDTO.setId(null);
			if (!Objects.equals(dto.getClient(), clientDTO)) {
				if (!clientValidation.getDocument().equals(dto.getClient().getDocument())) {
					if (clientRepository.existsByDocument(dto.getClient().getDocument())) {
						response.setMessage("El numero de documento ya se encuentra registrado");
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}
				} else {
					if (clientRepository.existsByDocument(dto.getClient().getDocument())) {
						response.setMessage("El numero de documento ya se encuentra registrado");
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}
				}
				if (!clientValidation.getPhone().equals(dto.getClient().getPhone())) {
					if (clientRepository.existsByPhone(dto.getClient().getPhone())) {
						response.setMessage("El numero de telefono ya se encuentra registrado");
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}
				} else {
					if (clientRepository.existsByPhone(dto.getClient().getPhone())) {
						response.setMessage("El numero de telefono ya se encuentra registrado");
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}
				}
				if (!clientValidation.getAddress().equals(dto.getClient().getAddress())) {
					if (clientRepository.existsByAddress(dto.getClient().getAddress())) {
						response.setMessage("La direccion ya se encuentra registrada");
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}
				} else {
					if (clientRepository.existsByAddress(dto.getClient().getAddress())) {
						response.setMessage("La direccion ya se encuentra registrada");
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}
				}
			}
		}
		Clients getClient = clientRepository.findByPhone(dto.getClient().getPhone());
		if (Objects.isNull(getClient)) {
			Optional<Order> getOrders = ordersRepository.findById(dto.getOrder().getId());
			if (!getOrders.isEmpty()) {
				response.setMessage(
						"Este Id de Orden " + String.valueOf(dto.getOrder().getId()) + " ya se encuentra registrada");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} else if (getOrders.isEmpty()) {
				clients = dtosInEntitys.convertClient(dto.getClient());
				if (Objects.nonNull(clients)) {
					dto.getOrder().setInfoClient(dto.getClient().getPhone());
					orders = dtosInEntitys.convertOrdersCreated(dto.getOrder());
					if (Objects.nonNull(orders)) {
						Schedules schedules = dtosInEntitys.convertSchedules(dto.getSchedules());
						if (Objects.nonNull(schedules)) {
							response.setMessage("Orden creada exitosamente");
							return new ResponseEntity<>(response, HttpStatus.CREATED);
						}
					} else {
						response.setMessage("No se ha podido registrar la orden");
						return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					response.setMessage("No se ha podido registrar el cliente");
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}
		} else {
			dto.getOrder().setInfoClient(dto.getClient().getPhone());
			Optional<Orders> getOrders = ordersRepository.findById(dto.getOrder().getId());
			if (getOrders.isEmpty()) {
				orders = dtosInEntitys.convertOrdersCreated(dto.getOrder());
				if (Objects.nonNull(orders)) {
					if (Objects.nonNull(dtosInEntitys.convertSchedules(dto.getSchedules()))) {
						response.setMessage("Orden creada exitosamente");
						return new ResponseEntity<>(response, HttpStatus.CREATED);
					}
				} else {
					response.setMessage("No se ha podido registrar la orden");
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				response.setMessage(
						"Este Id de Orden" + String.valueOf(dto.getOrder().getId()) + " ya se encuentra registrada");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		response.setMessage("Sin Datos Disponibles");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
*/
}
