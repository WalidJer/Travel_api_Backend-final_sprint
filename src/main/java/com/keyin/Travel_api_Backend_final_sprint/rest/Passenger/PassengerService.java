package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;


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
        return new PassengerDTO(passengerRepository.save(passenger));
    }

    public PassengerDTO updatePassenger(Long id, Passenger updatedPassenger) {
        Passenger existing = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existing.setFirstName(updatedPassenger.getFirstName());
        existing.setLastName(updatedPassenger.getLastName());
        existing.setPhoneNumber(updatedPassenger.getPhoneNumber());

        return new PassengerDTO(passengerRepository.save(existing));
    }

    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passengerRepository.delete(passenger);
    }
}
