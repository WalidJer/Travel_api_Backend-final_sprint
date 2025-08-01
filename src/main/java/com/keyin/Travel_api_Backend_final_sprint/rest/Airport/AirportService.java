package com.keyin.Travel_api_Sprint1.rest.Airport;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.AircraftRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AircraftRepository aircraftRepository;



    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AirportDTO> getAirportById(Long id) {
        return airportRepository.findById(id)
                .map(AirportDTO::new);
    }

    public AirportDTO createAirport(Airport airport) {
        return new AirportDTO(airportRepository.save(airport));
    }

    public AirportDTO updateAirport(Long id, Airport updatedAirport) {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        existingAirport.setName(updatedAirport.getName());
        existingAirport.setCode(updatedAirport.getCode());

        //  Handle City reference safely:
        if (updatedAirport.getCity() != null) {
            existingAirport.setCity(updatedAirport.getCity());
        }

        return new AirportDTO(airportRepository.save(existingAirport));
    }


    @Transactional
    public void deleteAirport(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));


        for (Aircraft aircraft : airport.getAircraftList()) {
            aircraft.getAirports().remove(airport);
            aircraftRepository.save(aircraft);
        }

        airportRepository.deleteById(id);
    }
}
