package com.keyin.Travel_api_Sprint1.rest.Airport;

public class SimpleAirportDTO {
    private Long id;
    private String name;
    private String code;

    public SimpleAirportDTO() {}

    public SimpleAirportDTO(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.code = airport.getCode();
    }

    // Getters and setters

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
