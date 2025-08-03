package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;

import com.keyin.Travel_api_Backend_final_sprint.rest.City.SimpleCityDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.SimpleFlightDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String passportNumber;

    private SimpleCityDTO city;
    private List<SimpleFlightDTO> flights;

    public PassengerDTO() {
    }

    public PassengerDTO(Passenger passenger) {
        if (passenger != null) {
            this.id = passenger.getId();
            this.firstName = passenger.getFirstName();
            this.lastName = passenger.getLastName();
            this.phoneNumber = passenger.getPhoneNumber();
            this.passportNumber = passenger.getPassportNumber();

            if (passenger.getCity() != null) {
                this.city = new SimpleCityDTO(passenger.getCity());
            }

            if (passenger.getFlights() != null) {
                this.flights = passenger.getFlights().stream()
                        .map(SimpleFlightDTO::new)
                        .collect(Collectors.toList());
            }
        }
    }

    // Getters and Setters

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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public SimpleCityDTO getCity() {
        return city;
    }

    public void setCity(SimpleCityDTO city) {
        this.city = city;
    }

    public List<SimpleFlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<SimpleFlightDTO> flights) {
        this.flights = flights;
    }
}
