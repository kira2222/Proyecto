package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.entity.Evidence;
import co.com.msservielectrogas.repository.IEvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evidences")
public class EvidenceController {

    @Autowired
    private IEvidenceRepository evidenceRepository;

    @GetMapping
    public List<Evidence> getAllEvidences() {
        return evidenceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evidence> getEvidenceById(@PathVariable Long id) {
        Optional<Evidence> evidence = evidenceRepository.findById(id);
        return evidence.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evidence createEvidence(@RequestBody Evidence evidence) {
        return evidenceRepository.save(evidence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evidence> updateEvidence(@PathVariable Long id, @RequestBody Evidence evidenceDetails) {
        Optional<Evidence> evidenceOptional = evidenceRepository.findById(id);
        if (evidenceOptional.isPresent()) {
            Evidence evidence = evidenceOptional.get();
            evidence.setPhotoUrl(evidenceDetails.getPhotoUrl());
            evidence.setOrderService(evidenceDetails.getOrderService());
            evidence.setCreatedAt(evidenceDetails.getCreatedAt());
            return ResponseEntity.ok(evidenceRepository.save(evidence));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvidence(@PathVariable Long id) {
        if (evidenceRepository.existsById(id)) {
            evidenceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
