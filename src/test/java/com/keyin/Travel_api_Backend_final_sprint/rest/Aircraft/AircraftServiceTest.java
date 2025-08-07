package com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.AirlineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AircraftService aircraftService;

    private Airline sampleAirline;
    private Aircraft sampleAircraft;

    @BeforeEach
    void setup() {
        sampleAirline = new Airline(1L, "Air Canada", "AC", null, null);
        sampleAircraft = new Aircraft(1L, "Boeing 737", 180, sampleAirline, null);
    }

    @Test
    void testGetAllAircrafts() {
        List<Aircraft> mockAircrafts = List.of(sampleAircraft);
        when(aircraftRepository.findAll()).thenReturn(mockAircrafts);

        List<AircraftDTO> result = aircraftService.getAllAircrafts();

        assertEquals(1, result.size());
        assertEquals("Boeing 737", result.getFirst().getModel());
        assertEquals(180, result.getFirst().getCapacity());
    }

    @Test
    void testCreateAircraftWithValidAirline() {
        // Arrange
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(sampleAirline));
        when(aircraftRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Aircraft inputAircraft = new Aircraft(null, "Airbus A320", 150, sampleAirline, null);

        // Act
        AircraftDTO result = aircraftService.createAircraft(inputAircraft);

        // Assert
        assertNotNull(result);
        assertEquals("Airbus A320", result.getModel());
        assertEquals(150, result.getCapacity());
        assertEquals("Air Canada", result.getAirline().getName());
    }

    @Test
    void testCreateAircraftWithMissingAirline() {
        Aircraft inputAircraft = new Aircraft(null, "Embraer 175", 88, null, null);
        when(aircraftRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AircraftDTO result = aircraftService.createAircraft(inputAircraft);

        assertNotNull(result);
        assertEquals("Embraer 175", result.getModel());
        assertNull(result.getAirline());
    }


}