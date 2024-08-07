package com.project.CarPro.controllers;

import com.project.CarPro.dto.request.DriverRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.DriverResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.model.Driver;
import com.project.CarPro.model.Manager;
import com.project.CarPro.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    DriverService driverService;
@Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/create")
    public ResponseEntity<DriverResponseDTO> addDriver (@RequestBody DriverRequestDTO driverRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.addDriver(driverRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getDriver(@PathVariable Long id) {
        DriverResponseDTO driver = driverService.getDriver(id);
        return ResponseEntity.status(HttpStatus.OK).body(driver);
    }
    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long driverId) {
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{driverId}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable Long driverId, @RequestBody DriverRequestDTO driverRequestDTO) {
        DriverResponseDTO driverResponseDTO = driverService.updateDriver(driverId, driverRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(driverResponseDTO);
    }
    //ToDo
    //getALLDRiversByCar
    @GetMapping("/{carId}")
    public ResponseEntity<List<Driver>> getALlDrivers(@PathVariable Long carId) {
        List<Driver> drivers = driverService.getAllDriversByCar(carId);
        return ResponseEntity.ok(drivers);

    }
    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }
}

