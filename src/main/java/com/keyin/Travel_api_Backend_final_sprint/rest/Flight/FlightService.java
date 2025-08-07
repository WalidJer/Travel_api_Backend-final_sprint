package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.AircraftRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.AirlineRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.AirportRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.GateRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Passenger.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private GateRepository gateRepository;


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

        if (flight.getAirline() != null && flight.getAirline().getId() != null) {
            flight.setAirline(airlineRepository.findById(flight.getAirline().getId()).orElse(null));
        }

        if (flight.getAircraft() != null && flight.getAircraft().getId() != null) {
            flight.setAircraft(aircraftRepository.findById(flight.getAircraft().getId()).orElse(null));
        }

        if (flight.getArrivalAirport() != null && flight.getArrivalAirport().getId() != null) {
            flight.setArrivalAirport(airportRepository.findById(flight.getArrivalAirport().getId()).orElse(null));
        }

        if (flight.getDepartureAirport() != null && flight.getDepartureAirport().getId() != null) {
            flight.setDepartureAirport(airportRepository.findById(flight.getDepartureAirport().getId()).orElse(null));
        }

        if (flight.getGate() != null && flight.getGate().getId() != null) {
            flight.setGate(gateRepository.findById(flight.getGate().getId()).orElse(null));
        }

        // Preserve bidirectional mapping
        if (flight.getPassengers() != null) {
            for (Passenger p : flight.getPassengers()) {
                p.getFlights().add(flight);
            }
        }

        return new FlightDTO(flightRepository.save(flight));
    }

    public FlightDTO updateFlight(Long id, Flight updatedFlight) {
        Flight existing = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existing.setFlightNumber(updatedFlight.getFlightNumber());
        existing.setStatus(updatedFlight.getStatus());
        existing.setDepartureTime(updatedFlight.getDepartureTime());
        existing.setArrivalTime(updatedFlight.getArrivalTime());
        existing.setGate(updatedFlight.getGate());
        existing.setDepartureAirport(updatedFlight.getDepartureAirport());
        existing.setArrivalAirport(updatedFlight.getArrivalAirport());
        existing.setAircraft(updatedFlight.getAircraft());
        existing.setAirline(updatedFlight.getAirline());

        // Maintain many-to-many consistency
        existing.getPassengers().clear();
        if (updatedFlight.getPassengers() != null) {
            existing.getPassengers().addAll(updatedFlight.getPassengers());
            for (Passenger p : updatedFlight.getPassengers()) {
                p.getFlights().add(existing);
            }
        }

        return new FlightDTO(flightRepository.save(existing));
    }

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // Remove the flight from all associated passengers
        if (flight.getPassengers() != null) {
            for (Passenger passenger : flight.getPassengers()) {
                passenger.getFlights().remove(flight);
            }
            flight.getPassengers().clear();
        }

        flightRepository.delete(flight);
    }


    public List<FlightDTO> getFlightsByDepartureAirport(Long airportId) {
        return flightRepository.findByDepartureAirportId(airportId).stream()
                .map(FlightDTO::new).collect(Collectors.toList());
    }

    public List<FlightDTO> getFlightsByArrivalAirport(Long airportId) {
        return flightRepository.findByArrivalAirportId(airportId).stream()
                .map(FlightDTO::new).collect(Collectors.toList());
    }

    public List<FlightDTO> searchByStatus(FlightStatus status) {
        return flightRepository.findByStatus(status)
                .stream().map(FlightDTO::new).collect(Collectors.toList());
    }

    public List<FlightDTO> searchByFlightNumber(String keyword) {
        return flightRepository.findByFlightNumberContainingIgnoreCase(keyword)
                .stream().map(FlightDTO::new).collect(Collectors.toList());
    }

    public List<FlightDTO> getFlightsByDepartureBetween(LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByDepartureTimeBetween(start, end)
                .stream().map(FlightDTO::new).collect(Collectors.toList());
    }

    public List<FlightDTO> getFlightsByArrivalBetween(LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByArrivalTimeBetween(start, end)
                .stream().map(FlightDTO::new).collect(Collectors.toList());
    }

    public List<FlightDTO> getFlightsByPassenger(Long passengerId) {
        return flightRepository.findByPassengerId(passengerId)
                .stream().map(FlightDTO::new).collect(Collectors.toList());
    }
}
