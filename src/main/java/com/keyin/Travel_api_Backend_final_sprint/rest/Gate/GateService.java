package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GateService {
    @Autowired
    private GateRepository gateRepository;
    @Autowired
    private AirportRepository airportRepository;

    public List<GateDTO> getAllGates() {
        return gateRepository.findAll().stream()
                .map(GateDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<GateDTO> getGateById(Long id) {
        return gateRepository.findById(id)
                .map(GateDTO::new);
    }

    public GateDTO createGate(Gate gate) {
        if (gate.getAirport() != null && gate.getAirport().getId() != null) {
            gate.setAirport(airportRepository.findById(gate.getAirport().getId()).orElse(null));
        }
        return new GateDTO(gateRepository.save(gate));
    }

    public GateDTO updateGate(Long id, Gate updatedGate) {
        Gate existing = gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found"));

        existing.setGateNumber(updatedGate.getGateNumber());
        existing.setTerminal(updatedGate.getTerminal());

        return new GateDTO(gateRepository.save(existing));
    }

    public void deleteGate(Long id) {
        Gate gate = gateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gate not found"));

        if (gate.getFlights() != null && !gate.getFlights().isEmpty()) {
            throw new RuntimeException("Cannot delete gate. It is assigned to one or more flights.");
        }

        gateRepository.delete(gate);
    }
}
