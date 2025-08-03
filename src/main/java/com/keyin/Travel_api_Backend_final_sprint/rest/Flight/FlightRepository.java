package com.keyin.Travel_api_Backend_final_sprint.rest.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportId(Long airportId);
    List<Flight> findByArrivalAirportId(Long airportId);

    List<Flight> findByStatus(FlightStatus status);
    List<Flight> findByFlightNumberContainingIgnoreCase(String flightNumber);

    List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Flight> findByArrivalTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT f FROM Flight f JOIN f.passengers p WHERE p.id = :passengerId")
    List<Flight> findByPassengerId(@Param("passengerId") Long passengerId);

}
