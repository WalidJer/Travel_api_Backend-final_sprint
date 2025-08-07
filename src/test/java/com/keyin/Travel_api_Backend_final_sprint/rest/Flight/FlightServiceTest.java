package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.AircraftRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.AirlineRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.AirportRepository;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.Gate;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.GateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock private AirlineRepository airlineRepository;
    @Mock private AircraftRepository aircraftRepository;
    @Mock private AirportRepository airportRepository;
    @Mock private GateRepository gateRepository;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void testGetAllFlights_returnsMappedDTOs() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setFlightNumber("AC123");
        flight.setStatus(FlightStatus.ON_TIME);

        Mockito.when(flightRepository.findAll()).thenReturn(List.of(flight));

        List<FlightDTO> result = flightService.getAllFlights();

        assertEquals(1, result.size());
        assertEquals("AC123", result.getFirst().getFlightNumber());
        assertEquals(FlightStatus.ON_TIME, result.getFirst().getStatus());
    }

    @Test
    public void testCreateFlight_withAllDependencies() {
        // Prepare dependencies
        Airline airline = new Airline(1L, "Air Canada", "AC", new ArrayList<>(), null);
        Aircraft aircraft = new Aircraft(1L, "Boeing 737", 150, airline, null);
        Gate gate = new Gate(1L, "G5", "T1", null, null);
        Airport departureAirport = new Airport(1L, "Toronto Pearson", "YYZ", null, null, null, null);
        Airport arrivalAirport = new Airport(2L, "Vancouver Intl", "YVR", null, null, null, null);

        Flight flight = new Flight(null, "AC456", FlightStatus.BOARDING,
                LocalDateTime.now(), LocalDateTime.now().plusHours(5),
                gate, new ArrayList<>(), departureAirport, arrivalAirport, aircraft, airline);

        Mockito.when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));
        Mockito.when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        Mockito.when(gateRepository.findById(1L)).thenReturn(Optional.of(gate));
        Mockito.when(airportRepository.findById(1L)).thenReturn(Optional.of(departureAirport));
        Mockito.when(airportRepository.findById(2L)).thenReturn(Optional.of(arrivalAirport));
        Mockito.when(flightRepository.save(Mockito.any(Flight.class))).thenAnswer(inv -> inv.getArgument(0));

        FlightDTO result = flightService.createFlight(flight);

        assertEquals("AC456", result.getFlightNumber());
        assertEquals(FlightStatus.BOARDING, result.getStatus());
        assertEquals("YYZ", result.getDepartureAirport().getCode());
        assertEquals("YVR", result.getArrivalAirport().getCode());
        assertEquals("Air Canada", result.getAirline().getName());
    }
}