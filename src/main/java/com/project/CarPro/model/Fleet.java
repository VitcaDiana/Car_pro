package com.project.CarPro.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fleet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fleetName;
    @Column
    private int totalNumberOfCars;


    @OneToMany(mappedBy = "fleet", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "fleet-car")
    private List<Car> listCar;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
