package com.keyin.Travel_api_Sprint1.rest.Aircraft;

public class SimpleAircraftDTO {
    private Long id;
    private String model;
    private String airlineName;

    public SimpleAircraftDTO() {}

    public SimpleAircraftDTO(Aircraft aircraft) {
        this.id = aircraft.getId();
        this.model = aircraft.getModel();
        this.airlineName = aircraft.getAirlineName();
    }

    // Getters and setters


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

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
