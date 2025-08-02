package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.SimpleAirportDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.SimpleFlightDTO;

import java.util.List;
import java.util.stream.Collectors;

public class GateDTO {

    private Long id;
    private String gateNumber;
    private SimpleAirportDTO airport;
    private List<SimpleFlightDTO> flights;

    public GateDTO() {
    }

    public GateDTO(Gate gate) {
        if (gate != null) {
            this.id = gate.getId();
            this.gateNumber = gate.getGateNumber();

            if (gate.getAirport() != null) {
                this.airport = new SimpleAirportDTO(gate.getAirport());
            }

            this.flights = (gate.getFlights() != null) ?
                    gate.getFlights().stream()
                            .map(SimpleFlightDTO::new)
                            .collect(Collectors.toList())
                    : null;
        }
    }

    // Getters and Setters

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

    public SimpleAirportDTO getAirport() {
        return airport;
    }

    public void setAirport(SimpleAirportDTO airport) {
        this.airport = airport;
    }

    public List<SimpleFlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<SimpleFlightDTO> flights) {
        this.flights = flights;
    }
}
