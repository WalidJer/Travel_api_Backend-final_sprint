package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gateNumber;

    @OneToMany(mappedBy = "gate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Gate() {
    }

    public Gate(Long id, String gateNumber, List<Flight> flights, Airport airport) {
        this.id = id;
        this.gateNumber = gateNumber;
        this.flights = flights;
        this.airport = airport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}
