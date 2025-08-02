package com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft;

import com.keyin.Travel_api_Backend_final_sprint.rest.Airline.Airline;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import jakarta.persistence.*;

import java.util.List;

public class SimpleAircraftDTO {

    private Long id;
    private String model;
    private int capacity;


    public SimpleAircraftDTO() {
    }

    public SimpleAircraftDTO(Aircraft aircraft){
        this.id = aircraft.getId();
        this.model = aircraft.getModel();
        this.capacity = aircraft.getCapacity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
