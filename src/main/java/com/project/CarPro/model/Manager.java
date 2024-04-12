package com.project.CarPro.model;

import jakarta.persistence.*;

@Entity

public class Manager extends User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @OneToOne(mappedBy = "manager")
    private Fleet fleet;
}
