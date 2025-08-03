package com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aircrafts")
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;
//
//
//    @GetMapping
//    public List<AircraftDTO> getAllAircrafts() {
//        return aircraftService.getAllAircrafts();
//    }
//
//    // GET aircraft by ID with full DTO
//    @GetMapping("/{id}")
//    public Optional<AircraftDTO> getAircraftById(@PathVariable Long id) {
//        return aircraftService.getAircraftById(id);
//    }
//
//    // POST aircraft with passengers and airports (proper linking)
//    @PostMapping
//    public AircraftDTO createAircraft(@RequestBody Aircraft aircraft) {
//        return aircraftService.createAircraft(aircraft);
//    }
//
//    //  PUT (update)
//    @PutMapping("/{id}")
//    public AircraftDTO updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft) {
//        return aircraftService.updateAircraft(id, aircraft);
//    }
//
//    // DELETE
//    @DeleteMapping("/{id}")
//    public void deleteAircraft(@PathVariable Long id) {
//        aircraftService.deleteAircraft(id);
//    }

    // Get all aircrafts
    @GetMapping
    public List<AircraftDTO> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    // Get aircraft by ID with proper 404 handling
    @GetMapping("/{id}")
    public ResponseEntity<AircraftDTO> getAircraftById(@PathVariable Long id) {
        return aircraftService.getAircraftById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create aircraft
    @PostMapping
    public ResponseEntity<AircraftDTO> createAircraft(@RequestBody Aircraft aircraft) {
        AircraftDTO created = aircraftService.createAircraft(aircraft);
        return ResponseEntity.status(201).body(created);
    }

    // Update aircraft
    @PutMapping("/{id}")
    public ResponseEntity<AircraftDTO> updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft) {
        AircraftDTO updated = aircraftService.updateAircraft(id, aircraft);
        return ResponseEntity.ok(updated);
    }

    // Delete aircraft
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }
}
