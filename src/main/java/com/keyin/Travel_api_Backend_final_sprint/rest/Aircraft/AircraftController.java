package com.keyin.Travel_api_Sprint1.rest.Aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.keyin.Travel_api_Sprint1.rest.Airport.SimpleAirportDTO;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/aircrafts")
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;


    @GetMapping
    public List<AircraftDTO> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    // GET aircraft by ID with full DTO
    @GetMapping("/{id}")
    public Optional<AircraftDTO> getAircraftById(@PathVariable Long id) {
        return aircraftService.getAircraftById(id);
    }

    // POST aircraft with passengers and airports (proper linking)
    @PostMapping
    public AircraftDTO createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftService.createAircraft(aircraft);
    }

    //  PUT (update)
    @PutMapping("/{id}")
    public AircraftDTO updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraft) {
        return aircraftService.updateAircraft(id, aircraft);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
    }

    // Question 3 route:
    @GetMapping("/{id}/airports")
    public List<SimpleAirportDTO> getAirportsByAircraft(@PathVariable Long id) {
        return aircraftService.getAirportsByAircraft(id);
    }
}
