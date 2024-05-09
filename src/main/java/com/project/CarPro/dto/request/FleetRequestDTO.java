package com.project.CarPro.dto.request;


public class FleetRequestDTO {
    private String fleetName;
    private int totalNumberOfCars;
    private Long managerId;

    public FleetRequestDTO(String fleetName, int totalNumberOfCars,Long managerId) {
        this.fleetName = fleetName;
        this.totalNumberOfCars = totalNumberOfCars;
       this.managerId = managerId;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public int getTotalNumberOfCars() {
        return totalNumberOfCars;
    }

    public void setTotalNumberOfCars(int totalNumberOfCars) {
        this.totalNumberOfCars = totalNumberOfCars;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
