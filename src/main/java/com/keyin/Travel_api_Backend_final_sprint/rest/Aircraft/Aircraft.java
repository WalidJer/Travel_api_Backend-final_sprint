package com.keyin.Travel_api_Sprint1.rest.Aircraft;

import jakarta.persistence.*;
import com.keyin.Travel_api_Sprint1.rest.Airport.Airport;
import com.keyin.Travel_api_Sprint1.rest.Passenger.Passenger;

import java.util.List;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String airlineName;
    private int capacity;


    @ManyToMany(mappedBy = "aircraftList")
    private List<Passenger> passengers;

    @ManyToMany
    @JoinTable(
            name = "aircraft_airport",
            joinColumns = @JoinColumn(name = "aircraft_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )

    private List<Airport> airports;


    public Aircraft() {
    }

    public Aircraft(String model, String airlineName, int capacity) {
        this.model = model;
        this.airlineName = airlineName;
        this.capacity = capacity;

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


    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }


    public String getAirlineName() {
        return airlineName;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public List<Airport> getAirports() {
        return airports;
    }
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }


}
