package com.keyin.Travel_api_Backend_final_sprint.rest.Airline;

import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import jakarta.persistence.*;

import java.util.List;

public class SimpleAirlineDTO {
    private Long id;
    private String name;
    private String code;

    public SimpleAirlineDTO() {
    }

    public SimpleAirlineDTO(Airline airline){
        this.id = airline.getId();
        this.name = airline.getName();
        this.code = airline.getCode();
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
