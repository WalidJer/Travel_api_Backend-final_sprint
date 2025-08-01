package com.keyin.Travel_api_Sprint1.rest.Airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCode(String code);
    Airport findByName(String name);
}
