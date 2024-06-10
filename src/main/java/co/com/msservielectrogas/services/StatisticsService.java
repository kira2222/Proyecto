package co.com.msservielectrogas.services;

import co.com.msservielectrogas.dto.TechnicianStatisticsDTO;
import co.com.msservielectrogas.dto.WarrantiesByTechnicianDTO;
import co.com.msservielectrogas.dto.WarrantiesByTypeDTO;
import co.com.msservielectrogas.dto.WarrantyStatisticsDTO;
import co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO; // Importa tu nuevo DTO
import co.com.msservielectrogas.dto.TechnicianEffectivenessDTO;
import co.com.msservielectrogas.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public List<TechnicianStatisticsDTO> getTechnicianStatistics() {
        return statisticsRepository.getTechnicianStatistics();
    }

    public List<TechnicianStatisticsDTO> getWarrantyTechnicianStatistics() {
        return statisticsRepository.getWarrantyTechnicianStatistics();
    }

    public List<WarrantyStatisticsDTO> getWarrantyStatistics() {
        return statisticsRepository.getWarrantyStatistics();
    }

    public List<ServiceTypeStatisticsDTO> getServiceTypeStatistics() {
        return statisticsRepository.getServiceTypeStatistics();
    }
    public List<TechnicianEffectivenessDTO> getTechnicianEffectivenessStatistics() {
        return statisticsRepository.getTechnicianEffectivenessStatistics();
    }
        public List<WarrantiesByTechnicianDTO> getWarrantiesByTechnician() {
        return statisticsRepository.getWarrantiesByTechnician();
    }
        public List<WarrantiesByTypeDTO> getWarrantiesByType() {
        return statisticsRepository.getWarrantiesByType();
    }
}
