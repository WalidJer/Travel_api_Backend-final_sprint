package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gates")
@CrossOrigin
public class GateController {

    @Autowired
    private GateService gateService;

    @GetMapping
    public ResponseEntity<List<GateDTO>> getAllGates() {
        return ResponseEntity.ok(gateService.getAllGates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GateDTO> getGateById(@PathVariable Long id) {
        return gateService.getGateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GateDTO> createGate(@RequestBody Gate gate) {
        return ResponseEntity.status(201).body(gateService.createGate(gate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GateDTO> updateGate(@PathVariable Long id, @RequestBody Gate gate) {
        return ResponseEntity.ok(gateService.updateGate(id, gate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGate(@PathVariable Long id) {
        gateService.deleteGate(id);
        return ResponseEntity.noContent().build();
    }
}
