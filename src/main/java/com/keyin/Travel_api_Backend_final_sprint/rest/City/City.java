package com.keyin.Travel_api_Backend_final_sprint.rest.City;
import com.keyin.Travel_api_Backend_final_sprint.rest.Airport.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.Passenger.Passenger;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String province;
    private int population;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Airport> airports;

    public City() {
    }

    public City(Long id, String name, String province, int population, List<Passenger> passengers, List<Airport> airports) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.population = population;
        this.passengers = passengers;
        this.airports = airports;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getProvince() {
        return province;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
}
