package com.keyin.Travel_api_Sprint1.rest.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;



    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public Optional<CityDTO> getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    public CityDTO createCity(@RequestBody City city) {
        return cityService.createCity(city);
    }

    @PutMapping("/{id}")
    public CityDTO updateCity(@PathVariable Long id, @RequestBody City city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }


    //  Question 1 route:
    @GetMapping("/{id}/airports")
    public List<SimpleAirportDTO> getAirportsByCity(@PathVariable Long id) {
        return cityService.getAirportsByCity(id);
    }


}
