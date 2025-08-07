package com.keyin.Travel_api_Backend_final_sprint.rest.Airport;

import com.keyin.Travel_api_Backend_final_sprint.rest.City.City;
import com.keyin.Travel_api_Backend_final_sprint.rest.City.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private AirportService airportService;

    private City mockCity;

    @BeforeEach
    public void setUp() {
        mockCity = new City();
        mockCity.setId(1L);
        mockCity.setName("Toronto");
        mockCity.setProvince("Ontario");
        mockCity.setPopulation(3000000);
    }

    @Test
    public void testGetAllAirports() {
        Airport a1 = new Airport();
        a1.setId(1L);
        a1.setName("YYZ");
        a1.setCode("Toronto Pearson");
        a1.setCity(mockCity);

        Airport a2 = new Airport();
        a2.setId(2L);
        a2.setName("YVR");
        a2.setCode("Vancouver Intl");
        a2.setCity(mockCity);

        when(airportRepository.findAll()).thenReturn(List.of(a1, a2));

        List<AirportDTO> result = airportService.getAllAirports();

        assertEquals(2, result.size());
        assertEquals("YYZ", result.get(0).getName());
        assertEquals("Toronto", result.get(0).getCity().getName());
    }

    @Test
    public void testCreateAirport_WithCity() {
        Airport input = new Airport();
        input.setName("Halifax Stanfield");
        input.setCode("YHZ");

        City city = new City();
        city.setId(1L);
        input.setCity(city);

        Airport expectedSaved = new Airport();
        expectedSaved.setId(10L);
        expectedSaved.setName("Halifax Stanfield");
        expectedSaved.setCode("YHZ");
        expectedSaved.setCity(mockCity);

        when(cityRepository.findById(1L)).thenReturn(Optional.of(mockCity));
        when(airportRepository.save(any(Airport.class))).thenReturn(expectedSaved);

        AirportDTO result = airportService.createAirport(input);

        assertNotNull(result);
        assertEquals("YHZ", result.getCode());
        assertEquals("Halifax Stanfield", result.getName());
        assertEquals("Toronto", result.getCity().getName()); // mockCity from DB
    }

    @Test
    public void testCreateAirport_WithoutCity() {
        Airport input = new Airport();
        input.setName("Test Airport");
        input.setCode("TEST");
        input.setCity(null);

        Airport saved = new Airport();
        saved.setId(99L);
        saved.setName("Test Airport");
        saved.setCode("TEST");

        when(airportRepository.save(any(Airport.class))).thenReturn(saved);

        AirportDTO result = airportService.createAirport(input);

        assertEquals("Test Airport", result.getName());
        assertNull(result.getCity()); // since no city was attached
    }

    @Test
    public void testCreateAirport_WithInvalidCityId() {
        Airport input = new Airport();
        input.setName("Fake Airport");
        input.setCode("FAK");

        City fakeCity = new City();
        fakeCity.setId(999L);
        input.setCity(fakeCity);

        when(cityRepository.findById(999L)).thenReturn(Optional.empty());

        Airport saved = new Airport();
        saved.setId(42L);
        saved.setName("Fake Airport");
        saved.setCode("FAK");
        saved.setCity(null);

        when(airportRepository.save(any(Airport.class))).thenReturn(saved);

        AirportDTO result = airportService.createAirport(input);

        assertEquals("FAK", result.getCode());
        assertNull(result.getCity());
    }
}