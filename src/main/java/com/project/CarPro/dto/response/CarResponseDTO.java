package com.project.CarPro.dto.response;

import com.project.CarPro.model.CarType;

public class CarResponseDTO {
    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private String color;
    private double mileage;
    private String registrationNumber;
    private CarType carType;
    private String vin;
    private String enodeId;

    public CarResponseDTO(){}

    public CarResponseDTO(Long id, String brand, String model, int productionYear, String color, double mileage, String registrationNumber, CarType carType,String vin, String enodeId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.color = color;
        this.mileage = mileage;
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.vin = vin;
        this.enodeId = enodeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
