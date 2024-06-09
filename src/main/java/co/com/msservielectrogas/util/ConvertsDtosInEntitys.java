package co.com.msservielectrogas.util;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.msservielectrogas.dto.ClientDTO;
import co.com.msservielectrogas.dto.ServicesDTO;
import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.Services;
import co.com.msservielectrogas.repository.IClientRepository;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.repository.ISchedulesRepository;
import co.com.msservielectrogas.repository.IServiceActivityRepository;
import co.com.msservielectrogas.repository.IServicesRepository;
import co.com.msservielectrogas.repository.IUsersRepository;

@Service
public class ConvertsDtosInEntitys {

	@Autowired
	private IClientRepository repository;

	@Autowired
	private IOrderRepository ordersRepository;

	@Autowired
	private IUsersRepository usersRepository;

	@Autowired
	private IServicesRepository servicesRepository;

	@Autowired
	private ISchedulesRepository schedulesRepository;

	@Autowired
	private IServiceActivityRepository serviceActivityRepository;

	public Clients convertClient(ClientDTO clientDTO) {
        Clients client = new Clients();
        client.setDocument(clientDTO.getDocument());
        client.setNames(clientDTO.getNames());
        client.setAddress(clientDTO.getAddress());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setPhone1(clientDTO.getPhone1());
        client.setPhone2(clientDTO.getPhone2());
        client.setNameOfApplicant(clientDTO.getNameOfApplicant());
        client.setNumberOrderVendor(clientDTO.getNumberOrderVendor());
        client.setType(clientDTO.getType()); // Corregido
        return repository.save(client);
    }

    public Clients convertClientUpdate(ClientDTO clientDTO, Clients clients) {
        Clients client = new Clients();
        client.setId(clients.getId());
        client.setDocument(
                !Objects.equals(clientDTO.getDocument(), clients.getDocument()) ? clientDTO.getDocument() : clients.getDocument());
        client.setNames(!Objects.equals(clientDTO.getNames(), clients.getNames()) ? clientDTO.getNames() : clients.getNames());
        client.setAddress(
                !Objects.equals(clientDTO.getAddress(), clients.getAddress()) ? clientDTO.getAddress() : clients.getAddress());
        client.setEmail(!Objects.equals(clientDTO.getEmail(), clients.getEmail()) ? clientDTO.getEmail() : clients.getEmail());
        client.setPhone(!Objects.equals(clientDTO.getPhone(), clients.getPhone()) ? clientDTO.getPhone() : clients.getPhone());
        client.setPhone1(!Objects.equals(clientDTO.getPhone1(), clients.getPhone1()) ? clientDTO.getPhone1() : clients.getPhone1());
        client.setPhone2(!Objects.equals(clientDTO.getPhone2(), clients.getPhone2()) ? clientDTO.getPhone2() : clients.getPhone2());
        client.setNameOfApplicant(
                !Objects.equals(clientDTO.getNameOfApplicant(), clients.getNameOfApplicant()) ? clientDTO.getNameOfApplicant()
                        : clients.getNameOfApplicant());
        client.setNumberOrderVendor(
                !Objects.equals(clientDTO.getNumberOrderVendor(), clients.getNumberOrderVendor()) ? clientDTO.getNumberOrderVendor()
                        : clients.getNumberOrderVendor());
        client.setType(!Objects.equals(clientDTO.getType(), clients.getType()) ? clientDTO.getType() : clients.getType());
        return repository.save(client);
    }

    public Services convertService(ServicesDTO dto) {
        Services services = new Services();
        services.setId(dto.getId());
        services.setServicesType(dto.getServicesType());
        services.setServicesDescription(dto.getServicesDescription());
        services.setPrice(dto.getPrice());
        return servicesRepository.save(services);
    }

    public Services convertServiceUpdate(ServicesDTO dto, Services service) {
        Services services = new Services();
        services.setId(service.getId());
        services.setServicesType(
                !Objects.equals(dto.getServicesType(), service.getServicesType()) ? dto.getServicesType() : service.getServicesType());
        services.setServicesDescription(
                !Objects.equals(dto.getServicesDescription(), service.getServicesDescription()) ? dto.getServicesDescription()
                        : service.getServicesDescription());
        services.setPrice(!Objects.equals(dto.getPrice(), service.getPrice()) ? dto.getPrice() : service.getPrice());
        return servicesRepository.save(services);
    }
	/*

	public Orders convertOrdersCreated(OrdersDTO dto) {
		Orders orders = new Orders();
		Date date = new Date();
		Services service = null;
		String observations = null;
		String newline = "&nbsp;";
		List<Services> services = new ArrayList<>();
		if (dto.getServices().size() >= 1) {
			for (ServicesDTO servicesDTO : dto.getServices()) {
				if (Objects.isNull(servicesDTO.getId())) {
					service = convertService(servicesDTO);
				}
				services.add(service);
			}
		}
		orders.setId(dto.getId());
		orders.setServices(services);
		if (Objects.nonNull(dto.getObservations())) {
			observations = new Date().toLocaleString() + " " + dto.getObservations() + newline;
		} else {
			observations = dto.getObservations();
		}
		orders.setObservations(observations);
		orders.setStatus(dto.getStatus());
		orders.setCreateAt(date);
		Clients client = repository.findByPhoneOrDocument(dto.getInfoClient(), dto.getInfoClient());
		orders.setClients(client);
		orders.setTotalCharged(dto.getTotalCharged());
		Orders ordersReturn = ordersRepository.save(orders);
		return ordersReturn;
	}

	public Orders convertOrdersUpdate(OrdersDTO dto, Orders order) {
		Orders orders = new Orders();
		try {
			Date date = new Date();
			String dateActually = new Date().toLocaleString();
			Services service = null;
			String nuevaObservation = null;
			String newline = "&nbsp;";
			List<Services> services = new ArrayList<>();
			orders.setId(order.getId() != dto.getId() ? order.getId() : dto.getId());
			if (dto.getServices().size() != order.getServices().size()) {
				for (ServicesDTO servicesDTO : dto.getServices()) {
					if (Objects.isNull(servicesDTO.getId())) {
						service = convertService(servicesDTO);
						servicesRepository.save(service);
						services.add(service);
					} else {
						service = convertService(servicesDTO);
						services.add(service);
					}
				}
			} else {
				for (ServicesDTO servicesDTO : dto.getServices()) {
					service = convertService(servicesDTO);
					services.add(service);
				}
			}
			orders.setServices(services);
			String observations = order.getObservations();
			if (Objects.nonNull(observations) && Objects.nonNull(dto.getObservations())) {
				nuevaObservation = observations + newline + dateActually + " " + dto.getObservations()
						+ newline;
			}else if(Objects.isNull(observations) && Objects.nonNull(dto.getObservations())) {
				nuevaObservation = dateActually + " " + dto.getObservations() + newline;
			}else if(Objects.nonNull(observations) && Objects.isNull(dto.getObservations())) {
				nuevaObservation = observations;
			}
			orders.setObservations(nuevaObservation);
			orders.setStatus(order.getStatus() != dto.getStatus() ? dto.getStatus() : order.getStatus());
			orders.setUpdateAt(date);
			orders.setCreateAt(order.getCreateAt());
			if (Objects.isNull(dto.getTotalCharged())) {
				orders.setTotalCharged(order.getTotalCharged());
			}else {
				orders.setTotalCharged(dto.getTotalCharged());
			}
			orders.setClients(order.getClients());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ordersRepository.save(orders);
	}

	public Schedules convertSchedules(SchedulesDTO dto) {
		Schedules schedulesRetorno = null;
		try {
			SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFor.parse(dto.getDate());
			Schedules schedules = new Schedules();
			schedules.setDate(date);
			schedules.setHour(dto.getHour());
			Optional<Orders> order = ordersRepository.findById(dto.getIdOrder());
			schedules.setOrders(order.get());
			Optional<Users> user = usersRepository.findById(dto.getIdUser());
			schedules.setUsers(user.get());
			schedulesRetorno = schedulesRepository.save(schedules);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return schedulesRetorno;
	}

	public Schedules convertSchedulesUpdate(SchedulesDTO dto, Schedules schedule) {
		Schedules schedulesRetorno = null;
		try {
			SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFor.parse(dto.getDate());
			Schedules schedules = new Schedules();
			schedules.setIdSchedule(schedule.getIdSchedule());
			schedules.setDate(date != schedule.getDate() ? date : schedule.getDate());
			schedules.setHour(dto.getHour() != schedule.getHour() ? dto.getHour() : schedule.getHour());
			Optional<Users> user = usersRepository.findById(dto.getIdUser());
			schedules.setUsers(user.get() != schedule.getUsers() ? user.get() : schedule.getUsers());
			schedules.setOrders(schedule.getOrders());
			schedulesRetorno = schedulesRepository.save(schedules);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return schedulesRetorno;
	}

	public ClientDTO convertEntityInDtoClient(Clients clients) {
		ClientDTO dto = new ClientDTO();
		dto.setId(clients.getId());
		dto.setNames(clients.getNames());
		dto.setAddress(clients.getAddress());
		dto.setDocument(clients.getDocument());
		dto.setEmail(clients.getEmail());
		dto.setPhone(clients.getPhone());
		dto.setPhone1(clients.getPhone1());
		dto.setPhone2(clients.getPhone2());
		dto.setNameOfApplicant(clients.getNameOfApplicant());
		dto.setNumberOrderVendor(clients.getNumberOrderVendor());
		dto.setType(clients.getType());
		return dto;
	}

	public OrdersDTO convertEntityInDtoOrders(Orders orders) {
		if (orders == null) {
			return null;
		}

		OrdersDTO dto = new OrdersDTO();
		dto.setId(orders.getId());

		// Formato opcional de la información del cliente (considerar un método separado)
		String infoCliente = "";
		if (orders.getClients() != null) {
			if (orders.getClients().getNames() != null) {
				infoCliente += orders.getClients().getNames();
			}
			if (orders.getClients().getAddress() != null) {
				infoCliente += " " + orders.getClients().getAddress();
			}
			if (orders.getClients().getPhone() != null) {
				infoCliente += " " + orders.getClients().getPhone();
			}
		}
		dto.setInfoClient(infoCliente);

		dto.setObservations(orders.getObservations());
		List<Services> servicios = orders.getServices() != null ? (List<Services>) orders.getServices() : Collections.emptyList();
		List<ServicesDTO> serviciosConvertidos = new ArrayList<>();
		for (Services servicio : servicios) {
			serviciosConvertidos.add(convertEntityInDtoService(servicio));
		}
		dto.setServices(serviciosConvertidos);
		dto.setStatus(orders.getStatus());
		dto.setTotalCharged(orders.getTotalCharged());
		return dto;
	}


	public ServicesDTO convertEntityInDtoService(Services service) {
		ServicesDTO dto = new ServicesDTO();
		dto.setId(service.getId());
		dto.setServicesType(service.getServicesType());
		dto.setServicesDescription(service.getServicesDescription());
		dto.setPrice(service.getPrice());
		return dto;
	}

	public UserDTO convertEntityInDtoUser(Users users) {
		UserDTO dto = new UserDTO();
		dto.setId(users.getId());
		dto.setName(users.getName());
		dto.setEmail(users.getEmail());
		dto.setType(users.getRol());
		return dto;
	}

	public SchedulesDTO convertEntityInDtoSchedules(Schedules schedules) {
		SchedulesDTO dto = new SchedulesDTO();
		dto.setId(schedules.getIdSchedule());
		dto.setDate(schedules.getDate().toString());
		dto.setHour(schedules.getHour());
		dto.setIdOrder(schedules.getOrders().getId());
		dto.setIdUser(schedules.getUsers().getId());
		return dto;
	}
	*/

	/*public ServiceActivityDTO convertEntityInDtoServiceActivity(ServiceActivity serviceActivity) {
		ServiceActivityDTO dto = new ServiceActivityDTO();
		dto.setId(serviceActivity.getId());
		dto.setServicesId(serviceActivity.getServices().getId().longValue());
		dto.setRegistrationDate(serviceActivity.getRegistrationDate());
		dto.setUserRegistrationId(serviceActivity.getUserRegistrationId().getId().longValue());
		dto.setDescription(serviceActivity.getDescription());
		dto.setPriority(serviceActivity.getPriority());
		dto.setStatus(serviceActivity.getStatus());
		dto.setRecordStatus(serviceActivity.getRecordStatus().longValue());
		dto.setWarrantyEndDate(serviceActivity.getWarrantyEndDate());
		return dto;
	}

	public ServiceActivity convertServiceActivity(ServiceActivityDTO serviceActivityDTO) {
		try {
			Optional<Services> optionalService = servicesRepository.findById(serviceActivityDTO.getServicesId().intValue());
			Optional<Users> optionalUser = usersRepository.findById(serviceActivityDTO.getUserRegistrationId().intValue());

			if (optionalService.isPresent() && optionalUser.isPresent()) {
				ServiceActivity serviceActivity = new ServiceActivity();
				serviceActivity.setRegistrationDate(serviceActivityDTO.getRegistrationDate());
				serviceActivity.setServices(optionalService.get());
				serviceActivity.setUserRegistrationId(optionalUser.get());
				serviceActivity.setDescription(serviceActivityDTO.getDescription());
				serviceActivity.setPriority(serviceActivityDTO.getPriority());
				serviceActivity.setStatus(serviceActivityDTO.getStatus());
				serviceActivity.setRecordStatus(serviceActivityDTO.getRecordStatus().intValue());
				serviceActivity.setWarrantyEndDate(serviceActivityDTO.getWarrantyEndDate());
				return serviceActivityRepository.save(serviceActivity);
			} else {
				throw new IllegalArgumentException("No se encontró el servicio o el usuario correspondiente");
			}
		} catch (Exception e) {
			System.out.println("Error al guardar la ServiceActivity: " + e.getMessage());
			return null;
		}
	}

	public ServiceActivity convertServiceActivityUpdate(ServiceActivityDTO dto, ServiceActivity serviceActivity) {
    ServiceActivity serviceActivityRetorno = null;
    try {
        SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
        ServiceActivity serviceActivitys = new ServiceActivity();
        serviceActivitys.setId(serviceActivity.getId());
        serviceActivitys.setServices(serviceActivity.getServices());
        serviceActivitys.setUserRegistrationId(serviceActivity.getUserRegistrationId());
		serviceActivitys.setDescription(dto.getDescription()!= serviceActivity.getDescription()? dto.getDescription() : serviceActivity.getDescription());
        serviceActivitys.setRegistrationDate(dto.getRegistrationDate()!= serviceActivity.getRegistrationDate()? dto.getRegistrationDate() : serviceActivity.getRegistrationDate());
        serviceActivitys.setPriority(dto.getPriority()!= serviceActivity.getPriority()? dto.getPriority() : serviceActivity.getPriority());
        serviceActivitys.setStatus(dto.getStatus()!= serviceActivity.getStatus()? dto.getStatus() : serviceActivity.getStatus());
        serviceActivitys.setRecordStatus(dto.getRecordStatus().intValue()!= serviceActivity.getRecordStatus()? dto.getRecordStatus().intValue() : serviceActivity.getRecordStatus());
        serviceActivitys.setWarrantyEndDate(dto.getWarrantyEndDate()!= serviceActivity.getWarrantyEndDate()? dto.getWarrantyEndDate() : serviceActivity.getWarrantyEndDate());
        serviceActivityRetorno = serviceActivityRepository.save(serviceActivitys);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return serviceActivityRetorno;
}*/
}