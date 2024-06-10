package co.com.msservielectrogas.controller;


import co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO;
import co.com.msservielectrogas.dto.TechnicianEffectivenessDTO;
import co.com.msservielectrogas.dto.TechnicianStatisticsDTO;
import co.com.msservielectrogas.dto.WarrantiesByTechnicianDTO;
import co.com.msservielectrogas.dto.WarrantiesByTypeDTO;
import co.com.msservielectrogas.dto.WarrantyStatisticsDTO;
import co.com.msservielectrogas.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/technician-orders")
    public List<TechnicianStatisticsDTO> getTechnicianStatistics() {
        return statisticsService.getTechnicianStatistics();
    }

    @GetMapping("/warranty-technician")
    public List<TechnicianStatisticsDTO> getWarrantyTechnicianStatistics() {
        return statisticsService.getWarrantyTechnicianStatistics();
    }
    @GetMapping("/service-types")
    public List<ServiceTypeStatisticsDTO> getServiceTypeStatistics() {
        return statisticsService.getServiceTypeStatistics();
    }
    @GetMapping("/warranty-types")
    public List<WarrantyStatisticsDTO> getWarrantyStatistics() {
        return statisticsService.getWarrantyStatistics();
    }
    @GetMapping("/technician-effectiveness")
    public List<TechnicianEffectivenessDTO> getTechnicianEffectivenessStatistics() {
        return statisticsService.getTechnicianEffectivenessStatistics();
    }
    @GetMapping("/warranties-by-technician")
    public List<WarrantiesByTechnicianDTO> getWarrantiesByTechnician() {
        return statisticsService.getWarrantiesByTechnician();
    }
        @GetMapping("/warranties-by-type")
    public List<WarrantiesByTypeDTO> getWarrantiesByType() {
        return statisticsService.getWarrantiesByType();
    }
}
