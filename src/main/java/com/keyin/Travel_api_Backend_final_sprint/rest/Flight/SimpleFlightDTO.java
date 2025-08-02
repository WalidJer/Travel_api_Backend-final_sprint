package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.Gate;
import com.keyin.Travel_api_Backend_final_sprint.rest.Passenger.Passenger;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class SimpleFlightDTO {

    private Long id;
    private String flightNumber;
    private FlightStatus status;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;


    public SimpleFlightDTO() {
    }

    public SimpleFlightDTO(Flight flight){
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.status = flight.getStatus();
        this.departureTime = flight.getDepartureTime();
        this.arrivalTime = flight.getArrivalTime();
    }

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
}
