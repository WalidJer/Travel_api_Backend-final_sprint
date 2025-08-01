package com.keyin.Travel_api_Sprint1.rest.Aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft , Long> {
    Aircraft findByModel(String model);
}
