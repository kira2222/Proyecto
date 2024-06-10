package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.entity.Warranty;
import co.com.msservielectrogas.repository.IOrderServiceRepository;
import co.com.msservielectrogas.repository.IWarrantyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WarrantyService {

    @Autowired
    private IWarrantyRepository warrantyRepository;

    @Autowired
    private IOrderServiceRepository orderServiceRepository;

    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    public Optional<Warranty> getWarrantyById(Long id) {
        return warrantyRepository.findById(id);
    }

    public Map<Month, Long> getWarrantyStatisticsByMonth() {
        List<Warranty> warranties = StreamSupport.stream(warrantyRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return warranties.stream()
                .collect(Collectors.groupingBy(
                        warranty -> convertToLocalDateViaInstant(warranty.getStartDate()).getMonth(),
                        Collectors.counting()
                ));
    }

    private LocalDate convertToLocalDateViaInstant(Date date) {
        return new java.util.Date(date.getTime()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Warranty saveWarranty(Warranty warranty) {
        return warrantyRepository.save(warranty);
    }

    public Warranty updateWarranty(Long id, Warranty warrantyDetails) {
        Warranty warranty = warrantyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warranty not found with id " + id));

        warranty.setReason(warrantyDetails.getReason());
        warranty.setStartDate(warrantyDetails.getStartDate());
        warranty.setEndDate(warrantyDetails.getEndDate());
        warranty.setOrderService(warrantyDetails.getOrderService());

        return warrantyRepository.save(warranty);
    }

    public void deleteWarranty(Long id) {
        Warranty warranty = warrantyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warranty not found with id " + id));

        warrantyRepository.delete(warranty);
    }

    public Map<String, Long> getServiceStatusStatistics() {
        List<OrderService> orderServices = orderServiceRepository.findAll();

        return orderServices.stream()
                .collect(Collectors.groupingBy(
                        orderService -> getStatusDescription(orderService.getStatus()),
                        Collectors.counting()
                ));
    }
    

    private String getStatusDescription(int status) {
        switch (status) {
            case 0:
                return "Cancelados";
            case 1:
                return "Realizados";
            case 2:
                return "Aplazados";
            case 3:
                return "Pendientes";
            default:
                return "Desconocido";
        }
    }
}
