package com.keyin.Travel_api_Sprint1.rest.Passenger;

import com.keyin.Travel_api_Sprint1.rest.Aircraft.SimpleAircraftDTO;
import com.keyin.Travel_api_Sprint1.rest.City.SimpleCityDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private SimpleCityDTO city;
    private List<SimpleAircraftDTO> aircrafts;

    public PassengerDTO() {}

    public PassengerDTO(Passenger passenger) {
        this.id = passenger.getId();
        this.firstName = passenger.getFirstName();
        this.lastName = passenger.getLastName();
        this.phoneNumber = passenger.getPhoneNumber();

        if (passenger.getCity() != null) {
            this.city = new SimpleCityDTO(passenger.getCity());
        }

        if (passenger.getAircraftList() != null) {
            this.aircrafts = passenger.getAircraftList().stream()
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
