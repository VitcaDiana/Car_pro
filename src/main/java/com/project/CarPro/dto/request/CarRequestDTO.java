package com.project.CarPro.dto.request;

import com.project.CarPro.model.CarType;

public class CarRequestDTO {
    private String brand;
    private String model;
    private int productionYear;
    private String color;
    private double mileage;
    private String registrationNumber;
    private CarType carType;
    private Long fleetId;
    private String vin;
    private String enodeId;


    public CarRequestDTO(String brand, String model, int productionYear, String color, double mileage, String registrationNumber, CarType carType, Long fleetId, String vin, String enodeId) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.color = color;
        this.mileage = mileage;
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.fleetId = fleetId;
        this.vin = vin;
        this.enodeId = enodeId;

    }

    public CarRequestDTO() {
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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Long getFleetId() {
        return fleetId;
    }

    public void setFleetId(Long fleetId) {
        this.fleetId = fleetId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getEnodeId() {
        return enodeId;
    }

    public void setEnodeId(String enodeId) {
        this.enodeId = enodeId;
    }
}
