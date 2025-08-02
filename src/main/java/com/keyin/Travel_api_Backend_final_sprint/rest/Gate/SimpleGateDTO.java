package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

public class SimpleGateDTO {

    private Long id;
    private String gateNumber;

    public SimpleGateDTO() {
    }

    public SimpleGateDTO(Gate gate){
        this.id = gate.getId();
        this.gateNumber = gate.getGateNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }
}
