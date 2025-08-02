package com.keyin.Travel_api_Backend_final_sprint.rest.Airport;
import com.keyin.Travel_api_Backend_final_sprint.rest.City.SimpleCityDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Flight.SimpleFlightDTO;
import com.keyin.Travel_api_Backend_final_sprint.rest.Gate.SimpleGateDTO;
import java.util.List;
import java.util.stream.Collectors;

public class AirportDTO {

    private Long id;
    private String name;
    private String code;
    private SimpleCityDTO city;
    private List<SimpleFlightDTO> departingFlights;
    private List<SimpleFlightDTO> arrivingFlights;
    private List<SimpleGateDTO> gates;

    public AirportDTO() {
    }

    public AirportDTO(Airport airport) {

        if( airport != null){
            this.id = airport.getId();
            this.name = airport.getName();
            this.code = airport.getCode();

            if(airport.getCity() != null){
                this.city = new SimpleCityDTO(airport.getCity());
            }

            this.departingFlights = (airport.getDepartingFlights() !=null) ?
                    airport.getDepartingFlights().stream()
                            .map(SimpleFlightDTO::new)
                            .collect(Collectors.toList())
                    : null;

            this.arrivingFlights = (airport.getArrivingFlights() !=null) ?
                    airport.getArrivingFlights().stream()
                            .map(SimpleFlightDTO::new)
                            .collect(Collectors.toList())
                    : null;

            this.gates = (airport.getGates() != null) ?
                    airport.getGates().stream()
                            .map(SimpleGateDTO::new)
                            .collect(Collectors.toList())
                    : null;

        }
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

    public SimpleCityDTO getCity() {
        return city;
    }

    public void setCity(SimpleCityDTO city) {
        this.city = city;
    }

    public List<SimpleFlightDTO> getDepartingFlights() {
        return departingFlights;
    }

    public void setDepartingFlights(List<SimpleFlightDTO> departingFlights) {
        this.departingFlights = departingFlights;
    }

    public List<SimpleFlightDTO> getArrivingFlights() {
        return arrivingFlights;
    }

    public void setArrivingFlights(List<SimpleFlightDTO> arrivingFlights) {
        this.arrivingFlights = arrivingFlights;
    }

    public List<SimpleGateDTO> getGates() {
        return gates;
    }

    public void setGates(List<SimpleGateDTO> gates) {
        this.gates = gates;
    }
}
