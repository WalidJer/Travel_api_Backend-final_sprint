package com.keyin.Travel_api_Backend_final_sprint.rest.Airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

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
        Airport saved = airportRepository.save(airport);
        return new AirportDTO(saved);
    }

    public AirportDTO updateAirport(Long id, Airport updatedAirport) {
        Airport existing = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        existing.setName(updatedAirport.getName());
        existing.setCode(updatedAirport.getCode());

        Airport saved = airportRepository.save(existing);
        return new AirportDTO(saved);
    }

    public void deleteAirport(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        airportRepository.delete(airport);
    }
}
