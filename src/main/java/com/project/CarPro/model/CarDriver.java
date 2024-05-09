package com.project.CarPro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CarDriver {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    LocalDate startDate;

    LocalDate endDate;
    Long fleetId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-cardriver")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonBackReference(value = "driver-cardriver")
    private Driver driver;

    public CarDriver() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Long getFleetId() {
        return fleetId;
    }

    public void setFleetId(Long fleetId) {
        this.fleetId = fleetId;
    }
}
