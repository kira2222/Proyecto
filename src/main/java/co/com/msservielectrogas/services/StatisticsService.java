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
            System.out.println("Fetching technician settlements from the repository");
            List<TechnicianSettlementDTO> settlements = statisticsRepository.getTechnicianSettlements();
            System.out.println("Fetched " + settlements.size() + " settlements");
            return settlements;
        }
    
        public ByteArrayInputStream exportTechnicianSettlementsToExcel() throws IOException {
            List<TechnicianSettlementDTO> settlements = getTechnicianSettlements();
            System.out.println("Exporting " + settlements.size() + " settlements to Excel");
    
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Technician Settlements");
    
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue(".");
                headerRow.createCell(1).setCellValue("Nombres");
                headerRow.createCell(2).setCellValue("Apellidos"); // Ajustar según la disponibilidad del dato
                headerRow.createCell(3).setCellValue("Total Cobrado");
                headerRow.createCell(4).setCellValue("Mes");
    
                int rowNum = 1;
                for (TechnicianSettlementDTO settlement : settlements) {
                    System.out.println("Processing settlement: " + settlement);
                    System.out.println("Document: " + settlement.getDocument());
                    System.out.println("Name: " + settlement.getName());
                    System.out.println("Total Charged: " + settlement.getTotalCharged());
                    System.out.println("Month: " + settlement.getMonth());
    
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(settlement.getDocument());
                    row.createCell(1).setCellValue(settlement.getName());
                    // Asumiendo que "LastNames" no está disponible en este contexto, se omite en la creación del archivo.
                    row.createCell(2).setCellValue(""); // Dejar vacío o ajustar según necesidad.
                    row.createCell(3).setCellValue(settlement.getTotalCharged());
                    row.createCell(4).setCellValue(settlement.getMonth());
                }
    
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                return new ByteArrayInputStream(outputStream.toByteArray());
            }
        }
}
