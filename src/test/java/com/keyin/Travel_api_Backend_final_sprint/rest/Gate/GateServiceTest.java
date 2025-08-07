package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GateServiceTest {

    @Mock
    private GateRepository gateRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private GateService gateService;

    private Airport mockAirport;

    @BeforeEach
    public void setup() {
        mockAirport = new Airport(1L, "Toronto Pearson", "YYZ", null, null, null, null);
    }

    @Test
    public void testGetAllGates() {
        Gate g1 = new Gate(1L, "A1", "Terminal 1", new ArrayList<>(), mockAirport);
        Gate g2 = new Gate(2L, "B2", "Terminal 2", new ArrayList<>(), mockAirport);

        List<Gate> mockGateList = List.of(g1, g2);
        when(gateRepository.findAll()).thenReturn(mockGateList);

        List<GateDTO> result = gateService.getAllGates();

        assertEquals(2, result.size());
        assertEquals("A1", result.getFirst().getGateNumber());
        assertEquals("Terminal 1", result.getFirst().getTerminal());
    }

    @Test
    public void testCreateGateWithExistingAirport() {
        Gate input = new Gate(null, "C3", "Terminal 3", null, mockAirport);

        // Simulate airport found
        when(airportRepository.findById(mockAirport.getId())).thenReturn(Optional.of(mockAirport));

        // Simulate saved gate (with ID now)
        Gate saved = new Gate(3L, "C3", "Terminal 3", null, mockAirport);
        when(gateRepository.save(input)).thenReturn(saved);

        GateDTO result = gateService.createGate(input);

        assertEquals(3L, result.getId());
        assertEquals("C3", result.getGateNumber());
        assertEquals("Terminal 3", result.getTerminal());
    }

    @Test
    public void testCreateGateWhenAirportNotFound() {
        Airport fakeAirport = new Airport(999L, "Fake", "XXX", null, null, null,null);
        Gate input = new Gate(null, "Z9", "Terminal Z", null, fakeAirport);

        when(airportRepository.findById(999L)).thenReturn(Optional.empty());
        Gate saved = new Gate(5L, "Z9", "Terminal Z", null, null);
        when(gateRepository.save(input)).thenReturn(saved);

        GateDTO result = gateService.createGate(input);

        assertEquals(5L, result.getId());
        assertEquals("Z9", result.getGateNumber());
        assertEquals("Terminal Z", result.getTerminal());
    }
}
