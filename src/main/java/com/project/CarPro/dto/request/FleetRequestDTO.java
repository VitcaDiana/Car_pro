package com.project.CarPro.dto.request;


public class FleetRequestDTO {
    private String fleetName;


    public FleetRequestDTO(String fleetName) {
        this.fleetName = fleetName;

    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }


}
