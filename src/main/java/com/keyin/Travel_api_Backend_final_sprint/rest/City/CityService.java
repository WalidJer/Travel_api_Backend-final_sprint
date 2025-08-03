package com.keyin.Travel_api_Backend_final_sprint.rest.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CityDTO> getCityById(Long id) {
        return cityRepository.findById(id)
                .map(CityDTO::new);
    }

    public CityDTO createCity(City city) {
        City saved = cityRepository.save(city);
        return new CityDTO(saved);
    }

    public CityDTO updateCity(Long id, City updatedCity) {
        City existing = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        existing.setName(updatedCity.getName());
        existing.setProvince(updatedCity.getProvince());
        existing.setPopulation(updatedCity.getPopulation());

        City saved = cityRepository.save(existing);
        return new CityDTO(saved);
    }

    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        if (!city.getPassengers().isEmpty()) {
            throw new RuntimeException("Cannot delete city. It's assigned to passengers.");
        }

        cityRepository.delete(city);
    }
}
