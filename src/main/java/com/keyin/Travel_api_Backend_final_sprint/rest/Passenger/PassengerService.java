package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;


import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(PassengerDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<PassengerDTO> getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .map(PassengerDTO::new);
    }

    public PassengerDTO createPassenger(Passenger passenger) {
        if (passenger.getFlights() != null) {
            for (Flight f : passenger.getFlights()) {
                f.getPassengers().add(passenger); // set both sides
            }
        }

        return new PassengerDTO(passengerRepository.save(passenger));
    }

    public PassengerDTO updatePassenger(Long id, Passenger updatedPassenger) {
        Passenger existing = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existing.setFirstName(updatedPassenger.getFirstName());
        existing.setLastName(updatedPassenger.getLastName());
        existing.setPhoneNumber(updatedPassenger.getPhoneNumber());
        existing.setPassportNumber(updatedPassenger.getPassportNumber());
        existing.setCity(updatedPassenger.getCity());

        existing.getFlights().clear();
        if (updatedPassenger.getFlights() != null) {
            existing.getFlights().addAll(updatedPassenger.getFlights());
            for (Flight f : updatedPassenger.getFlights()) {
                f.getPassengers().add(existing);
            }
        }

        return new PassengerDTO(passengerRepository.save(existing));
    }

    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        // Remove the passenger from all associated flights
        if (passenger.getFlights() != null) {
            for (Flight flight : passenger.getFlights()) {
                flight.getPassengers().remove(passenger);
            }
            passenger.getFlights().clear();
        }

        passengerRepository.delete(passenger);
    }
}
