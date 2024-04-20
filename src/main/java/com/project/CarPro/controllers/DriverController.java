package com.project.CarPro.controllers;

import com.project.CarPro.dto.DriverRequestDTO;
import com.project.CarPro.dto.ManagerRequestDTO;
import com.project.CarPro.model.Driver;
import com.project.CarPro.model.Manager;
import com.project.CarPro.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {
    DriverService driverService;
@Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/create")
    public ResponseEntity<Driver> addDriver (@RequestBody DriverRequestDTO driverRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.addDriver(driverRequestDTO));
    }
}

