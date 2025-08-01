package com.keyin.Travel_api_Sprint1.rest.Aircraft;


import com.keyin.Travel_api_Sprint1.rest.Airport.Airport;
import com.keyin.Travel_api_Sprint1.rest.Airport.AirportRepository;
import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;
import com.keyin.Travel_api_Sprint1.rest.Passenger.Passenger;
import com.keyin.Travel_api_Sprint1.rest.Passenger.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;


@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AirportRepository airportRepository;



    public List<AircraftDTO> getAllAircrafts() {
        return aircraftRepository.findAll().stream()
                .map(AircraftDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AircraftDTO> getAircraftById(Long id) {
        return aircraftRepository.findById(id)
                .map(AircraftDTO::new);
    }


    public AircraftDTO createAircraft(Aircraft aircraft) {
        if (aircraft.getPassengers() != null) {
            List<Passenger> passengers = aircraft.getPassengers().stream()
                    .map(p -> passengerRepository.findById(p.getId())
                            .orElseThrow(() -> new RuntimeException("Passenger not found")))
                    .collect(Collectors.toList());
            aircraft.setPassengers(passengers);

            // Set reverse side BEFORE saving
            for (Passenger passenger : passengers) {
                passenger.getAircraftList().add(aircraft);
            }
        }

        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        return new AircraftDTO(savedAircraft);
    }



    public AircraftDTO updateAircraft(Long id, Aircraft updatedAircraft) {
        Aircraft existingAircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        existingAircraft.setModel(updatedAircraft.getModel());
        existingAircraft.setAirlineName(updatedAircraft.getAirlineName());
        existingAircraft.setCapacity(updatedAircraft.getCapacity());


        return new AircraftDTO(aircraftRepository.save(existingAircraft));
    }




    public void deleteAircraft(Long id) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        for (Passenger passenger : aircraft.getPassengers()) {
            passenger.getAircraftList().remove(aircraft);
            passengerRepository.save(passenger);
        }

        for (Airport airport : aircraft.getAirports()) {
            airport.getAircraftList().remove(aircraft);
            airportRepository.save(airport);
        }

        aircraftRepository.delete(aircraft);
    }

    // Question 3: What airports do aircraft take off from and land at?
    public List<SimpleAirportDTO> getAirportsByAircraft(Long id) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));
        return aircraft.getAirports().stream()
                .map(SimpleAirportDTO::new)
                .collect(Collectors.toList());
    }
}
