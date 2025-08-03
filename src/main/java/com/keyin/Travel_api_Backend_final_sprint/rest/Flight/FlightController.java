package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
@CrossOrigin
public class FlightController {


    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FlightDTO> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.status(201).body(flightService.createFlight(flight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departures/{airportId}")
    public ResponseEntity<List<FlightDTO>> getDepartures(@PathVariable Long airportId) {
        return ResponseEntity.ok(flightService.getFlightsByDepartureAirport(airportId));
    }

    @GetMapping("/arrivals/{airportId}")
    public ResponseEntity<List<FlightDTO>> getArrivals(@PathVariable Long airportId) {
        return ResponseEntity.ok(flightService.getFlightsByArrivalAirport(airportId));
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<FlightDTO>> searchByStatus(@RequestParam FlightStatus status) {
        return ResponseEntity.ok(flightService.searchByStatus(status));
    }

    @GetMapping("/search/flightNumber")
    public ResponseEntity<List<FlightDTO>> searchByFlightNumber(@RequestParam String flightNumber) {
        return ResponseEntity.ok(flightService.searchByFlightNumber(flightNumber));
    }

    @GetMapping("/departures")
    public List<FlightDTO> getFlightsByDepartureDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return flightService.getFlightsByDepartureBetween(start, end);
    }

    //  Filter by arrival date range
    @GetMapping("/arrivals")
    public List<FlightDTO> getFlightsByArrivalDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return flightService.getFlightsByArrivalBetween(start, end);
    }

    // 👤 Flights for a passenger
    @GetMapping("/passenger/{passengerId}")
    public List<FlightDTO> getFlightsByPassenger(@PathVariable Long passengerId) {
        return flightService.getFlightsByPassenger(passengerId);
    }
}
