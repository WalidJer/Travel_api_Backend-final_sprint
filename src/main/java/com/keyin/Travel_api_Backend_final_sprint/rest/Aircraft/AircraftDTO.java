package com.keyin.Travel_api_Sprint1.rest.Aircraft;

import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;
import com.keyin.Travel_api_Sprint1.rest.Passenger.SimplePassengerDTO;
import java.util.List;
import java.util.stream.Collectors;

public class AircraftDTO {
    private Long id;
    private String model;
    private String airlineName;
    private int capacity;
    private List<SimplePassengerDTO> passengers;
    private List<SimpleAirportDTO> airports;

    public AircraftDTO() {}

    public AircraftDTO(Aircraft aircraft) {
        this.id = aircraft.getId();
        this.model = aircraft.getModel();
        this.airlineName = aircraft.getAirlineName();
        this.capacity = aircraft.getCapacity();

        if (aircraft.getPassengers() != null) {
            this.passengers = aircraft.getPassengers().stream()
                    .map(SimplePassengerDTO::new)
                    .collect(Collectors.toList());
        }

        if (aircraft.getAirports() != null) {
            this.airports = aircraft.getAirports().stream()
                    .map(SimpleAirportDTO::new)
                    .collect(Collectors.toList());
        }
    }

    // Getters and setters


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

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<SimplePassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<SimplePassengerDTO> passengers) {
        this.passengers = passengers;
    }

    public List<SimpleAirportDTO> getAirports() {
        return airports;
    }

    public void setAirports(List<SimpleAirportDTO> airports) {
        this.airports = airports;
    }
}
