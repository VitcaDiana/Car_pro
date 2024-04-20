package com.project.CarPro.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Driver extends User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;
    @Column
    private String telephone;

    @OneToMany(mappedBy = "driver", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference(value = "driver-cardriver")
    private List<CarDriver> carDriverList;

    public Driver() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<CarDriver> getCarDriverList() {
        return carDriverList;
    }

    public void setCarDriverList(List<CarDriver> carDriverList) {
        this.carDriverList = carDriverList;
    }
}
