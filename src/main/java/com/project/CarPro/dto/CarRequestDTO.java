package com.project.CarPro.dto;

public class CarRequestDTO {
    private String brand;
    private String model;
    private int productionYear;
    private String color;
    private double mileage;
    private String registrationNumber;
    private String carType;
    private Long fleetId;

    public CarRequestDTO(String brand, String model, int productionYear, String color, double mileage, String registrationNumber, String carType, Long fleetId) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.color = color;
        this.mileage = mileage;
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.fleetId = fleetId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Long getFleetId() {
        return fleetId;
    }

    public void setFleetId(Long fleetId) {
        this.fleetId = fleetId;
    }
}
