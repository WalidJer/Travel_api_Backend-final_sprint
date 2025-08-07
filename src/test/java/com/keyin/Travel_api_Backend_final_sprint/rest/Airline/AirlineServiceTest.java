package com.keyin.Travel_api_Backend_final_sprint.rest.Airline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirlineServiceTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    private Airline acAirline;

    @BeforeEach
    void setUp() {
        acAirline = new Airline(1L, "Air Canada", "AC", new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testGetAllAirlines() {
        List<Airline> airlines = List.of(acAirline);
        when(airlineRepository.findAll()).thenReturn(airlines);

        List<AirlineDTO> result = airlineService.getAllAirlines();

        assertEquals(1, result.size());
        assertEquals("Air Canada", result.getFirst().getName());
        assertEquals("AC", result.getFirst().getCode());
    }

    @Test
    void testGetAirlineById_Found() {
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(acAirline));

        Optional<AirlineDTO> result = airlineService.getAirlineById(1L);

        assertTrue(result.isPresent());
        assertEquals("Air Canada", result.get().getName());
    }

    @Test
    void testGetAirlineById_NotFound() {
        when(airlineRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<AirlineDTO> result = airlineService.getAirlineById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateAirline() {
        Airline input = new Airline(null, "WestJet", "WS", null, null);
        Airline saved = new Airline(2L, "WestJet", "WS", new ArrayList<>(), new ArrayList<>());

        when(airlineRepository.save(input)).thenReturn(saved);

        AirlineDTO result = airlineService.createAirline(input);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("WestJet", result.getName());
        assertEquals("WS", result.getCode());
    }
}