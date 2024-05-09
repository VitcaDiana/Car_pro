package com.project.CarPro.dto.response;

public class DriverResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String telephone;

    public DriverResponseDTO(Long id,String name, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }
    public DriverResponseDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;

    }
}
