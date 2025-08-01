package com.keyin.Travel_api_Sprint1.rest.City;

public class SimpleCityDTO {

    private Long id;
    private String name;
    private String province;

    public SimpleCityDTO() {}

    public SimpleCityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.province = city.getProvince();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
