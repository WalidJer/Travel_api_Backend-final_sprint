package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;

import com.keyin.Travel_api_Backend_final_sprint.rest.City.SimpleCityDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.SimpleFlightDTO;

public class PassengerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private SimpleCityDTO city;
    private SimpleFlightDTO flight;

    public PassengerDTO() {
    }

    public PassengerDTO(Passenger passenger) {
        if (passenger != null) {
            this.id = passenger.getId();
            this.firstName = passenger.getFirstName();
            this.lastName = passenger.getLastName();
            this.phoneNumber = passenger.getPhoneNumber();

            if (passenger.getCity() != null) {
                this.city = new SimpleCityDTO(passenger.getCity());
            }

            if (passenger.getFlight() != null) {
                this.flight = new SimpleFlightDTO(passenger.getFlight());
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

    public SimpleCityDTO getCity() {
        return city;
    }

    public void setCity(SimpleCityDTO city) {
        this.city = city;
    }

    public SimpleFlightDTO getFlight() {
        return flight;
    }

    public void setFlight(SimpleFlightDTO flight) {
        this.flight = flight;
    }
}
