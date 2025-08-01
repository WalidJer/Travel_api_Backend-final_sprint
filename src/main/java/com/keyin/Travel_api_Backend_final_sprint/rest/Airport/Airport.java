package com.keyin.Travel_api_Sprint1.rest.Airport;

import jakarta.persistence.*;
import com.keyin.Travel_api_Sprint1.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Sprint1.rest.City.City;

import java.util.List;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(mappedBy = "airports")
    private List<Aircraft> aircraftList;

    public Airport() {
    }

    public Airport(String name, String code, City city) {
        this.name = name;
        this.code = code;
        this.city = city;
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

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }


    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }
    public void setAircraftList(List<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }
}
