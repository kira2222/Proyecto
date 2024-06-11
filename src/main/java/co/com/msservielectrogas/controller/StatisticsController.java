package co.com.msservielectrogas.controller;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import co.com.msservielectrogas.dto.ServiceReportDTO;
import co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO;
import co.com.msservielectrogas.dto.TechnicianEffectivenessDTO;
import co.com.msservielectrogas.dto.TechnicianSettlementDTO;
import co.com.msservielectrogas.dto.TechnicianStatisticsDTO;
import co.com.msservielectrogas.dto.WarrantiesByTechnicianDTO;
import co.com.msservielectrogas.dto.WarrantiesByTypeDTO;
import co.com.msservielectrogas.dto.WarrantyStatisticsDTO;
import co.com.msservielectrogas.services.StatisticsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    @GetMapping("/service-report")
    public ResponseEntity<byte[]> exportServiceReportToExcel() throws IOException {
        List<ServiceReportDTO> reportData = statisticsService.getServiceReports();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Service Report");
            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("Cédula");
            headerRow.createCell(1).setCellValue("Nombres");
            headerRow.createCell(2).setCellValue("Apellidos");
            headerRow.createCell(3).setCellValue("Tipo de Servicio");
            headerRow.createCell(4).setCellValue("Valor Cobrado");
            headerRow.createCell(5).setCellValue("Fecha de Ejecución");

            int rowNum = 1;
            for (ServiceReportDTO data : reportData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getDocument());
                row.createCell(3).setCellValue(data.getServicesDescription());
                row.createCell(4).setCellValue(data.getTotalCharged());
                row.createCell(5).setCellValue(data.getOrderServiceDate().toString());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=service_report.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());
        }
    }

    @GetMapping("/technician-settlement")
    public ResponseEntity<byte[]> exportTechnicianSettlementToExcel() throws IOException {
        List<TechnicianSettlementDTO> settlementData = statisticsService.getTechnicianSettlements();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Technician Settlement");
            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("Cédula");
            headerRow.createCell(1).setCellValue("Nombres");
            headerRow.createCell(2).setCellValue("Apellidos");
            headerRow.createCell(3).setCellValue("Total Cobrado");
            headerRow.createCell(4).setCellValue("Mes");

            int rowNum = 1;
            for (TechnicianSettlementDTO data : settlementData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getDocument());
                row.createCell(3).setCellValue(data.getTotalCharged());
 
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=technician_settlement.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());
        }
    }
}
