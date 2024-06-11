package co.com.msservielectrogas.services;

import co.com.msservielectrogas.dto.TechnicianStatisticsDTO;
import co.com.msservielectrogas.dto.WarrantiesByTechnicianDTO;
import co.com.msservielectrogas.dto.WarrantiesByTypeDTO;
import co.com.msservielectrogas.dto.WarrantyStatisticsDTO;
import co.com.msservielectrogas.dto.ServiceReportDTO;
import co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO; // Importa tu nuevo DTO
import co.com.msservielectrogas.dto.TechnicianEffectivenessDTO;
import co.com.msservielectrogas.dto.TechnicianSettlementDTO;
import co.com.msservielectrogas.repository.StatisticsRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        public List<ServiceReportDTO> getServiceReports() {
            return statisticsRepository.getServiceReport();
        }
    
        public List<TechnicianSettlementDTO> getTechnicianSettlements() {
            return statisticsRepository.getTechnicianSettlements();
        }
    
        public ByteArrayInputStream exportServiceReportsToExcel() throws IOException {
            List<ServiceReportDTO> reports = getServiceReports();
    
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Service Reports");
    
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Document");
                headerRow.createCell(1).setCellValue("Names");
                headerRow.createCell(3).setCellValue("Service Type");
                headerRow.createCell(4).setCellValue("Total Charged");
                headerRow.createCell(5).setCellValue("Service Date");
    
                int rowNum = 1;
                for (ServiceReportDTO report : reports) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(report.getDocument());
      
                }
    
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                return new ByteArrayInputStream(outputStream.toByteArray());
            }
        }
    

}
