package com.keyin.Travel_api_Sprint1.rest.Passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.SimpleAircraftDTO;
import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
@CrossOrigin
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Optional<PassengerDTO> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    public PassengerDTO createPassenger(@RequestBody Passenger passenger) {
        return passengerService.createPassenger(passenger);
    }

    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(id, passenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    //  Question 2 route:
    @GetMapping("/{id}/aircrafts")
    public List<SimpleAircraftDTO> getAircraftsByPassenger(@PathVariable Long id) {
        return passengerService.getAircraftsByPassenger(id);
    }

    //  Question 4 route:
    @GetMapping("/{id}/airports")
    public List<SimpleAirportDTO> getAirportsUsedByPassenger(@PathVariable Long id) {
        return passengerService.getAirportsUsedByPassenger(id);
    }
}
