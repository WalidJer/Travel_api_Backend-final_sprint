package com.keyin.Travel_api_Backend_final_sprint.rest.Airline;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airlines")
@CrossOrigin
public class AirlineController {


    @Autowired
    private AirlineService airlineService;

    @GetMapping
    public List<AirlineDTO> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineDTO> getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AirlineDTO> createAirline(@RequestBody Airline airline) {
        AirlineDTO created = airlineService.createAirline(airline);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineDTO> updateAirline(@PathVariable Long id, @RequestBody Airline airline) {
        AirlineDTO updated = airlineService.updateAirline(id, airline);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }
}
