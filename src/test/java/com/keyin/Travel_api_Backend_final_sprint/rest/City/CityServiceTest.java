package com.keyin.Travel_api_Backend_final_sprint.rest.City;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void testGetAllCities() {
        City c1 = new City();
        c1.setId(1L);
        c1.setName("Toronto");
        c1.setProvince("Ontario");
        c1.setPopulation(2800000);

        City c2 = new City();
        c2.setId(2L);
        c2.setName("Vancouver");
        c2.setProvince("British Columbia");
        c2.setPopulation(631000);

        List<City> mockCities = List.of(c1, c2);
        Mockito.when(cityRepository.findAll()).thenReturn(mockCities);

        List<CityDTO> result = cityService.getAllCities();

        assertEquals(2, result.size());
        assertEquals("Toronto", result.getFirst().getName());
        assertEquals("Ontario", result.getFirst().getProvince());
    }

}
