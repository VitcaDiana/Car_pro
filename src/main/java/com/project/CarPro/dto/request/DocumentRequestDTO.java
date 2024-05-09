package com.project.CarPro.dto.request;

import java.time.LocalDate;

public class DocumentRequestDTO {
    private String name;
    private LocalDate expirationDate;
    private LocalDate startDate;

    public DocumentRequestDTO(String name, LocalDate expirationDate, LocalDate startDate) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
