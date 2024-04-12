package com.project.CarPro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Documents {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-document")
    private Car car;

    @Column
    private String name;

    @Column
    private LocalDate expirationDate;

}
