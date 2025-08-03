package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassengerDTO>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody Passenger passenger) {
        return ResponseEntity.status(201).body(passengerService.createPassenger(passenger));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
        return ResponseEntity.ok(passengerService.updatePassenger(id, passenger));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
}
