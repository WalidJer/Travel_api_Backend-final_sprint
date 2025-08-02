package com.keyin.Travel_api_Backend_final_sprint.rest.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.SimpleAircraftDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.SimpleFlightDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

public class AirlineDTO {

    private Long id;
    private String name;
    private String code;
    private List<SimpleAircraftDTO> aircrafts;
    private List<SimpleFlightDTO> flights;

    public AirlineDTO() {
    }

    public AirlineDTO(Airline airline){

        if(airline != null){

            this.id = airline.getId();
            this.name = airline.getName();
            this.code = airline.getCode();

            this.aircrafts = (airline.getAircrafts() != null) ?
                    airline.getAircrafts().stream()
                            .map(SimpleAircraftDTO::new)
                            .collect(Collectors.toList())
                    : null;

            this.flights = (airline.getFlights() != null) ?
                    airline.getFlights().stream()
                            .map(SimpleFlightDTO::new)
                            .collect(Collectors.toList())
                    : null;
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SimpleAircraftDTO> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<SimpleAircraftDTO> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<SimpleFlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<SimpleFlightDTO> flights) {
        this.flights = flights;
    }
}
