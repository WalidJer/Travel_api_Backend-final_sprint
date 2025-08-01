package com.keyin.Travel_api_Sprint1.rest.City;

import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;
import com.keyin.Travel_api_Sprint1.rest.Passenger.SimplePassengerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CityDTO {
    private Long id;
    private String name;
    private String province;
    private int population;
    private List<SimplePassengerDTO> passengers;
    private List<SimpleAirportDTO> airports;

    public CityDTO() {}

    public CityDTO(City city) {
        if (city != null) {
            this.id = city.getId();
            this.name = city.getName();
            this.province = city.getProvince();
            this.population = city.getPopulation();

            this.passengers = (city.getPassengers() != null) ?
                    city.getPassengers().stream()
                            .map(SimplePassengerDTO::new)
                            .collect(Collectors.toList())
                    : null;

            this.airports = (city.getAirports() != null) ?
                    city.getAirports().stream()
                            .map(SimpleAirportDTO::new)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
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
