package com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.SimpleAirlineDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.SimpleFlightDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class AircraftDTO {

    private Long id;
    private String model;
    private int capacity;
    private SimpleAirlineDTO airline;
    private List<SimpleFlightDTO> flights;

    public AircraftDTO() {
    }

    public AircraftDTO(Aircraft aircraft){

        if(aircraft != null){
            this.id = aircraft.getId();
            this.model = aircraft.getModel();
            this.capacity = aircraft.getCapacity();

            this.flights = (aircraft.getFlights() != null) ?
                    aircraft.getFlights().stream()
                            .map(SimpleFlightDTO::new)
                            .collect(Collectors.toList())
                    : null;

            if (aircraft.getAirline() != null) {
                this.airline= new SimpleAirlineDTO(aircraft.getAirline());
            }
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public SimpleAirlineDTO getAirline() {
        return airline;
    }

    public void setAirline(SimpleAirlineDTO airline) {
        this.airline = airline;
    }

    public List<SimpleFlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<SimpleFlightDTO> flights) {
        this.flights = flights;
    }
}
