package com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.City.City;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import com.keyin.Travel_api_Backend_final_sprint.rest.Passenger.Passenger;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private int capacity;


    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights;


    public Aircraft() {
    }

    public Aircraft(Long id, String model, int capacity, Airline airline, List<Flight> flights) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.airline = airline;
        this.flights = flights;
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

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
