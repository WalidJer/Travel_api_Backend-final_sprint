package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.SimpleAircraftDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.SimpleAirlineDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.SimpleAirportDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.SimpleGateDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Passenger.SimplePassengerDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightDTO {

    private Long id;
    private String flightNumber;
    private FlightStatus status;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private SimpleGateDTO gate;
    private List<SimplePassengerDTO> passengers;
    private SimpleAirportDTO departureAirport;
    private SimpleAirportDTO arrivalAirport;
    private SimpleAircraftDTO aircraft;
    private SimpleAirlineDTO airline;

    public FlightDTO() {
    }

    public FlightDTO(Flight flight) {
        if (flight != null) {
            this.id = flight.getId();
            this.flightNumber = flight.getFlightNumber();
            this.status = flight.getStatus();
            this.departureTime = flight.getDepartureTime();
            this.arrivalTime = flight.getArrivalTime();

            if (flight.getGate() != null) {
                this.gate = new SimpleGateDTO(flight.getGate());
            }

            if (flight.getPassengers() != null) {
                this.passengers = flight.getPassengers().stream()
                        .map(SimplePassengerDTO::new)
                        .collect(Collectors.toList());
            }

            if (flight.getDepartureAirport() != null) {
                this.departureAirport = new SimpleAirportDTO(flight.getDepartureAirport());
            }

            if (flight.getArrivalAirport() != null) {
                this.arrivalAirport = new SimpleAirportDTO(flight.getArrivalAirport());
            }

            if (flight.getAircraft() != null) {
                this.aircraft = new SimpleAircraftDTO(flight.getAircraft());
            }

            if (flight.getAirline() != null) {
                this.airline = new SimpleAirlineDTO(flight.getAirline());
            }
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public SimpleGateDTO getGate() {
        return gate;
    }

    public void setGate(SimpleGateDTO gate) {
        this.gate = gate;
    }

    public List<SimplePassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<SimplePassengerDTO> passengers) {
        this.passengers = passengers;
    }

    public SimpleAirportDTO getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(SimpleAirportDTO departureAirport) {
        this.departureAirport = departureAirport;
    }

    public SimpleAirportDTO getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(SimpleAirportDTO arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public SimpleAircraftDTO getAircraft() {
        return aircraft;
    }

    public void setAircraft(SimpleAircraftDTO aircraft) {
        this.aircraft = aircraft;
    }

    public SimpleAirlineDTO getAirline() {
        return airline;
    }

    public void setAirline(SimpleAirlineDTO airline) {
        this.airline = airline;
    }
}
