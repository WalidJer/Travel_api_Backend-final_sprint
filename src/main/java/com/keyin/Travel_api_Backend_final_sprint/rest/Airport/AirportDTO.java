package com.keyin.Travel_api_Sprint1.rest.Airport;

import com.keyin.Travel_api_Sprint1.rest.Aircraft.SimpleAircraftDTO;
import com.keyin.Travel_api_Sprint1.rest.City.SimpleCityDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AirportDTO {
    private Long id;
    private String name;
    private String code;
    private SimpleCityDTO city;
    private List<SimpleAircraftDTO> aircrafts;

    public AirportDTO() {}

    public AirportDTO(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.code = airport.getCode();

        if (airport.getCity() != null) {
            this.city = new SimpleCityDTO(airport.getCity());
        }

        if (airport.getAircraftList() != null) {
            this.aircrafts = airport.getAircraftList().stream()
                    .map(SimpleAircraftDTO::new)
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

    public SimpleCityDTO getCity() {
        return city;
    }

    public void setCity(SimpleCityDTO city) {
        this.city = city;
    }

    public List<SimpleAircraftDTO> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<SimpleAircraftDTO> aircrafts) {
        this.aircrafts = aircrafts;
    }
}
