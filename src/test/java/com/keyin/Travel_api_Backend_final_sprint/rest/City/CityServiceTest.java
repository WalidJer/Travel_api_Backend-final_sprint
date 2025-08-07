package com.keyin.Travel_api_Backend_final_sprint.rest.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City toronto;
    private City vancouver;
    private City calgary;

    @BeforeEach
    void setUp() {
        toronto = new City();
        toronto.setId(1L);
        toronto.setName("Toronto");
        toronto.setProvince("Ontario");
        toronto.setPopulation(2800000);

        vancouver = new City();
        vancouver.setId(2L);
        vancouver.setName("Vancouver");
        vancouver.setProvince("British Columbia");
        vancouver.setPopulation(631000);

        calgary = new City();
        calgary.setId(3L);
        calgary.setName("Calgary");
        calgary.setProvince("Alberta");
        calgary.setPopulation(1230000);
    }

    @Test
    public void testGetAllCities() {
        Mockito.when(cityRepository.findAll()).thenReturn(List.of(toronto, vancouver));

        List<CityDTO> result = cityService.getAllCities();

        assertEquals(2, result.size());
        assertEquals("Toronto", result.getFirst().getName());
        assertEquals("Ontario", result.getFirst().getProvince());
    }

    @Test
    public void testCreateCity() {
        City inputCity = new City();
        inputCity.setName("Calgary");
        inputCity.setProvince("Alberta");
        inputCity.setPopulation(1230000);

        Mockito.when(cityRepository.save(inputCity)).thenReturn(calgary);

        CityDTO result = cityService.createCity(inputCity);

        assertEquals(3L, result.getId());
        assertEquals("Calgary", result.getName());
        assertEquals("Alberta", result.getProvince());
        assertEquals(1230000, result.getPopulation());
    }

    //Edge cases
    @Test
    public void testGetAllCities_WhenNoCitiesExist() {
        Mockito.when(cityRepository.findAll()).thenReturn(List.of());

        List<CityDTO> result = cityService.getAllCities();

        assertEquals(0, result.size(), "Expected an empty list when no cities exist");
    }

    @Test
    public void testGetAllCities_WithNullFields() {
        City brokenCity = new City();
        brokenCity.setId(4L);
        // name, province, population left as null

        Mockito.when(cityRepository.findAll()).thenReturn(List.of(brokenCity));

        List<CityDTO> result = cityService.getAllCities();

        assertEquals(1, result.size());
        assertNull(result.getFirst().getName());
    }

    @Test
    public void testCreateCity_WithNullFields() {
        City inputCity = new City();

        City savedCity = new City();
        savedCity.setId(5L);

        Mockito.when(cityRepository.save(inputCity)).thenReturn(savedCity);

        CityDTO result = cityService.createCity(inputCity);

        assertEquals(5L, result.getId());
        assertNull(result.getName());
    }

    @Test
    public void testCreateCity_WhenRepositoryThrows() {
        City inputCity = new City();
        inputCity.setName("Halifax");

        Mockito.when(cityRepository.save(Mockito.any())).thenThrow(new RuntimeException("DB failure"));

        Assertions.assertThrows(RuntimeException.class, () -> cityService.createCity(inputCity));
    }
}