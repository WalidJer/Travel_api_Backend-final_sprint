package com.keyin.Travel_api_Sprint1.rest.Passenger;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.AircraftRepository;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.SimpleAircraftDTO;
import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<PassengerDTO> getAllPassengers() {
        return  passengerRepository.findAll().stream()
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
        Passenger existingPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existingPassenger.setFirstName(updatedPassenger.getFirstName());
        existingPassenger.setLastName(updatedPassenger.getLastName());
        existingPassenger.setPhoneNumber(updatedPassenger.getPhoneNumber());

        // Handle City reference safely:
        if (updatedPassenger.getCity() != null) {
            existingPassenger.setCity(updatedPassenger.getCity());
        }

        return new PassengerDTO(passengerRepository.save(existingPassenger));
    }

    @Transactional
    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        for (Aircraft aircraft : passenger.getAircraftList()) {
            aircraft.getPassengers().remove(passenger);
            aircraftRepository.save(aircraft);
        }

        passengerRepository.deleteById(id);
    }


    //  Question 2: What aircraft has each passenger flown on?
    public List<SimpleAircraftDTO> getAircraftsByPassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        return passenger.getAircraftList().stream()
                .map(SimpleAircraftDTO::new)
                .collect(Collectors.toList());
    }

    //  Question 4: What airports have passengers used?
    public List<SimpleAirportDTO> getAirportsUsedByPassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        return passenger.getAircraftList().stream()
                .flatMap(aircraft -> aircraft.getAirports().stream())
                .distinct()
                .map(SimpleAirportDTO::new)
                .collect(Collectors.toList());
    }
}
