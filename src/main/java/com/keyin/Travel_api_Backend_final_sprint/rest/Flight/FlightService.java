package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(FlightDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<FlightDTO> getFlightById(Long id) {
        return flightRepository.findById(id)
                .map(FlightDTO::new);
    }

    public FlightDTO createFlight(Flight flight) {
        return new FlightDTO(flightRepository.save(flight));
    }

    public FlightDTO updateFlight(Long id, Flight updatedFlight) {
        Flight existing = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existing.setFlightNumber(updatedFlight.getFlightNumber());
        existing.setStatus(updatedFlight.getStatus());
        existing.setDepartureTime(updatedFlight.getDepartureTime());
        existing.setArrivalTime(updatedFlight.getArrivalTime());

        return new FlightDTO(flightRepository.save(existing));
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        flightRepository.delete(flight);
    }
}
