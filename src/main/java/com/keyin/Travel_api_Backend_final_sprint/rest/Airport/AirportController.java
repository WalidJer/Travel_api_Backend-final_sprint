package com.keyin.Travel_api_Backend_final_sprint.rest.Airport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin
public class AirportController {


    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<List<AirportDTO>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDTO> getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AirportDTO> createAirport(@RequestBody Airport airport) {
        return ResponseEntity.status(201).body(airportService.createAirport(airport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportDTO> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return ResponseEntity.ok(airportService.updateAirport(id, airport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}
