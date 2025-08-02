package com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AircraftService {


    @Autowired
    private AircraftRepository aircraftRepository;

    // Get all aircrafts as DTOs
    public List<AircraftDTO> getAllAircrafts() {
        return aircraftRepository.findAll().stream()
                .map(AircraftDTO::new)
                .collect(Collectors.toList());
    }

    // Get a single aircraft by ID as DTO
    public Optional<AircraftDTO> getAircraftById(Long id) {
        return aircraftRepository.findById(id)
                .map(AircraftDTO::new);
    }

    // Create a new aircraft
    public AircraftDTO createAircraft(Aircraft aircraft) {
        Aircraft saved = aircraftRepository.save(aircraft);
        return new AircraftDTO(saved);
    }

    // Update an existing aircraft
    public AircraftDTO updateAircraft(Long id, Aircraft updatedAircraft) {
        Aircraft existing = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        existing.setModel(updatedAircraft.getModel());
        existing.setCapacity(updatedAircraft.getCapacity());

        Aircraft saved = aircraftRepository.save(existing);
        return new AircraftDTO(saved);
    }

    // Delete aircraft by ID
    public void deleteAircraft(Long id) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        aircraftRepository.delete(aircraft);
    }
}
