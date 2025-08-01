package com.keyin.Travel_api_Sprint1.rest.City;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.AircraftRepository;
import com.keyin.Travel_api_Sprint1.rest.Airport.Airport;
import com.keyin.Travel_api_Sprint1.rest.Airport.AirportRepository;
import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;
import com.keyin.Travel_api_Sprint1.rest.Passenger.Passenger;
import com.keyin.Travel_api_Sprint1.rest.Passenger.PassengerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {


    @Autowired
    private  CityRepository cityRepository;
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private AircraftRepository aircraftRepository;



    public List<CityDTO> getAllCities() {
        return  cityRepository.findAll().stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CityDTO > getCityById(Long id) {
        return cityRepository.findById(id)
                .map(CityDTO::new);

    }

    public CityDTO createCity(City city) {
        return new CityDTO(cityRepository.save(city));
    }

    public CityDTO updateCity(Long id, City updatedCity) {
        City existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        existingCity.setName(updatedCity.getName());
        existingCity.setProvince(updatedCity.getProvince());
        existingCity.setPopulation(updatedCity.getPopulation());

        return new CityDTO(cityRepository.save(existingCity));
    }

    @Transactional
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found"));

        for (Airport airport : city.getAirports()) {
            for (Aircraft aircraft : airport.getAircraftList()) {
                aircraft.getAirports().remove(airport);
                aircraftRepository.save(aircraft);
            }
            airportRepository.delete(airport);
        }

        for (Passenger passenger : city.getPassengers()) {
            passengerRepository.delete(passenger);
        }

        cityRepository.delete(city);
    }




    //  Question 1: What airports are there in each city?

    public List<SimpleAirportDTO> getAirportsByCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        return city.getAirports().stream()
                .map(SimpleAirportDTO::new)
                .collect(Collectors.toList());
    }
}
