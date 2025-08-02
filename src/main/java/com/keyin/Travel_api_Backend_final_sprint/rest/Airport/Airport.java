package com.keyin.Travel_api_Backend_final_sprint.rest.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.Aircraft.Aircraft;
import com.keyin.Travel_api_Backend_final_sprint.rest.City.City;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.Flight;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.Gate;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> departingFlights;

    @OneToMany(mappedBy = "arrivalAirport",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> arrivingFlights;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gate> gates;



    public Airport() {
    }

    public Airport(Long id, String name, String code, City city, List<Flight> departingFlights, List<Flight> arrivingFlights, List<Gate> gates) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
        this.departingFlights = departingFlights;
        this.arrivingFlights = arrivingFlights;
        this.gates = gates;
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

    public List<Flight> getDepartingFlights() {
        return departingFlights;
    }

    public void setDepartingFlights(List<Flight> departingFlights) {
        this.departingFlights = departingFlights;
    }

    public List<Flight> getArrivingFlights() {
        return arrivingFlights;
    }

    public void setArrivingFlights(List<Flight> arrivingFlights) {
        this.arrivingFlights = arrivingFlights;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }
}
