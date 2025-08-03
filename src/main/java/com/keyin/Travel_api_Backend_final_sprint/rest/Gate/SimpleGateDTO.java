package com.keyin.Travel_api_Backend_final_sprint.rest.Gate;

public class SimpleGateDTO {

    private Long id;
    private String gateNumber;
    private String terminal;

    public SimpleGateDTO() {
    }

    public SimpleGateDTO(Gate gate){
        this.id = gate.getId();
        this.gateNumber = gate.getGateNumber();
        this.terminal = gate.getTerminal();
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
