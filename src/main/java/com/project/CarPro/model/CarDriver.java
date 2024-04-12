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

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-cardriver")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonBackReference(value = "driver-cardriver")
    private Driver driver;


}
