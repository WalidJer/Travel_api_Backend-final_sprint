package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;
import com.keyin.Travel_api_Backend_final_sprint.rest.City.City;
import com.keyin.Travel_api_Backend_final_sprint.rest.City.CityRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.FlightRepository;
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
public class PassengerServiceTest {

    @Mock private PassengerRepository passengerRepository;
    @Mock private CityRepository cityRepository;
    @Mock private FlightRepository flightRepository;

    @InjectMocks private PassengerService passengerService;

    private Passenger testPassenger;
    private City testCity;
    private Flight testFlight;

    @BeforeEach
    void setUp() {
        testCity = new City(1L, "Toronto", "ON", 2800000, null, null);

        testFlight = new Flight();
        testFlight.setId(1L);
        testFlight.setPassengers(new ArrayList<>()); // Important for bidirectional add

        testPassenger = new Passenger(
                1L,
                "John",
                "Doe",
                "1234567890",
                "P1234567",
                testCity,
                List.of(testFlight)
        );
    }

    @Test
    void testGetAllPassengers() {
        when(passengerRepository.findAll()).thenReturn(List.of(testPassenger));

        List<PassengerDTO> result = passengerService.getAllPassengers();

        assertEquals(1, result.size());
        assertEquals("John", result.getFirst().getFirstName());
    }

    @Test
    void testGetPassengerById_Found() {
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(testPassenger));

        Optional<PassengerDTO> result = passengerService.getPassengerById(1L);

        assertTrue(result.isPresent());
        assertEquals("Doe", result.get().getLastName());
    }

    @Test
    void testGetPassengerById_NotFound() {
        when(passengerRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<PassengerDTO> result = passengerService.getPassengerById(999L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreatePassenger_WithCityAndFlights() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(testCity));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(testFlight));
        when(passengerRepository.save(any(Passenger.class))).thenAnswer(i -> i.getArguments()[0]);

        Passenger passengerToSave = new Passenger(
                null,
                "Alice",
                "Smith",
                "9999999999",
                "P9999999",
                new City(1L, null, null, 0, null, null), //
                List.of(new Flight(1L, null, null, null, null, null, null, null, null, null, null))
        );

        PassengerDTO result = passengerService.createPassenger(passengerToSave);

        assertNotNull(result);
        assertEquals("Alice", result.getFirstName());

        // Ensure bidirectional relationship worked
        assertTrue(testFlight.getPassengers().contains(passengerToSave));
    }

    @Test
    void testCreatePassenger_ThrowsIfCityNotFound() {
        when(cityRepository.findById(99L)).thenReturn(Optional.empty());

        Passenger p = new Passenger(null, "Tom", "Notfound", "123", "P999", new City(99L, null, null, 0, null, null), null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> passengerService.createPassenger(p));

        assertEquals("City not found", exception.getMessage());
    }

    @Test
    void testCreatePassenger_ThrowsIfFlightNotFound() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(testCity));
        when(flightRepository.findById(55L)).thenReturn(Optional.empty());

        Passenger p = new Passenger(
                null, "Eve", "Error", "111", "P888", testCity,
                List.of(new Flight(55L, null, null, null, null, null, null, null, null, null, null))
        );

        RuntimeException ex = assertThrows(RuntimeException.class, () -> passengerService.createPassenger(p));
        assertEquals("Flight not found", ex.getMessage());
    }
}