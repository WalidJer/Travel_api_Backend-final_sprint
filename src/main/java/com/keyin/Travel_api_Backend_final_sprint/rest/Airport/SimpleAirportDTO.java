package com.keyin.Travel_api_Backend_final_sprint.rest.Airport;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import jakarta.persistence.*;

import java.util.List;

public class SimpleAirportDTO {
    private Long id;
    private String name;
    private String code;

    public SimpleAirportDTO() {
    }

    public  SimpleAirportDTO(Airport airport){
        this.id = airport.getId();
        this.name = airport.getName();
        this.code = airport.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
