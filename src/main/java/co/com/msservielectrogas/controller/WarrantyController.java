package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.entity.Warranty;
import co.com.msservielectrogas.services.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Month;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/warranties")
public class WarrantyController {











    @Autowired
    private WarrantyService warrantyService;

    @GetMapping
    public List<Warranty> getAllWarranties() {
        return warrantyService.getAllWarranties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warranty> getWarrantyById(@PathVariable Long id) {
        return warrantyService.getWarrantyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/statistics/month")
    public Map<Month, Long> getWarrantyCountByMonth() {
        return warrantyService.getWarrantyStatisticsByMonth();
    }

    @PostMapping
    public Warranty createWarranty(@RequestBody Warranty warranty) {
        return warrantyService.saveWarranty(warranty);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Warranty> updateWarranty(@PathVariable Long id, @RequestBody Warranty warrantyDetails) {
        return ResponseEntity.ok(warrantyService.updateWarranty(id, warrantyDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable Long id) {
        warrantyService.deleteWarranty(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/statistics/status")
    public ResponseEntity<Map<String, Long>> getServiceStatusStatistics() {
        Map<String, Long> statistics = warrantyService.getServiceStatusStatistics();
        return ResponseEntity.ok(statistics);
    }
}
