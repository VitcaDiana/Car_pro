package com.project.CarPro.controllers;

import com.project.CarPro.dto.CarRequestDTO;
import com.project.CarPro.model.Car;
import com.project.CarPro.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    CarService carService;
@Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

@PostMapping("/{driverId}")
    public Car addCarToDriver(@PathVariable Long driverId,@RequestBody CarRequestDTO carRequestDTO,@RequestParam(required = false)Long fleetId) {
        return carService.addCarToDriver(driverId,carRequestDTO,fleetId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}

