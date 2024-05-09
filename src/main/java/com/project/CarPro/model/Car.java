package com.project.CarPro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brand;

    @Column
    private String model;
    @Column
    private int productionYear;

    @Column
    private String color;
    @Column
    private double mileage;
    @Column
    private String registrationNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "car-document")
    private List<Documents> listDocuments;

    @ManyToOne
    @JoinColumn(name = "fleet_id")
    @JsonBackReference(value = "fleet-car")
    private Fleet fleet;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "car-expenses")
    private List<Expenses> listExpenses;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference(value = "car-cardriver")
    private List<CarDriver> carDriverList;



    public Car() {
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

    public List<Documents> getListDocuments() {
        return listDocuments;
    }

    public void setListDocuments(List<Documents> listDocuments) {
        this.listDocuments = listDocuments;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

    public List<Expenses> getListExpenses() {
        return listExpenses;
    }

    public void setListExpenses(List<Expenses> listExpenses) {
        this.listExpenses = listExpenses;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<CarDriver> getCarDriverList() {
        return carDriverList;
    }

    public void setCarDriverList(List<CarDriver> carDriverList) {
        this.carDriverList = carDriverList;
    }


}
