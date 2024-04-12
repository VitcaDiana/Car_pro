package com.project.CarPro.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Driver extends User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "driver", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference(value = "driver-cardriver")
    private List<CarDriver> carDriverList;



}
