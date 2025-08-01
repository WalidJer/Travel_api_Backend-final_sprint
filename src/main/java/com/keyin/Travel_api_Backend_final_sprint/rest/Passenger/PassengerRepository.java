package com.keyin.Travel_api_Backend_final_sprint.rest.Passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByFirstName(String firstName);
    Passenger findByPhoneNumber(String phoneNumber);
}
