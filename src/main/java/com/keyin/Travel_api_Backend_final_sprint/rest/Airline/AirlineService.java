package com.keyin.Travel_api_Backend_final_sprint.rest.Airline;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.AircraftDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;
    // Get all airlines as DTOs
    public List<AirlineDTO> getAllAirlines(){
        return airlineRepository.findAll().stream()
                .map(AirlineDTO::new)
                .collect(Collectors.toList());
    }

    // Get a single airline by ID as DTO
    public Optional<AirlineDTO> getAirlineById(Long id){
        return airlineRepository.findById(id)
                .map(AirlineDTO::new);
    }

    // create a new airline
    public AirlineDTO createAirline(Airline airline){
       Airline saved = airlineRepository.save(airline);
        return new AirlineDTO(saved);
    }

    public  AirlineDTO updateAirline(Long id, Airline updatedAirline){

        Airline existing = airlineRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Airline not found"));

        existing.setName(updatedAirline.getName());
        existing.setCode(updatedAirline.getCode());

        Airline saved = airlineRepository.save(existing);
        return new AirlineDTO(saved);
    }

    public void deleteAirline(Long id){
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Airline not found"));

        airlineRepository.delete(airline);
    }


}
