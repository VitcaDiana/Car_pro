package com.project.CarPro.controllers;

import com.project.CarPro.dto.request.CarRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.CarResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.model.Car;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/{driverId}")
    public CarResponseDTO addCarToDriver(@PathVariable Long driverId, @RequestBody CarRequestDTO carRequestDTO, @RequestParam(required = false) Long fleetId) {
        return carService.addCarToDriver(driverId, carRequestDTO, fleetId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{carId}/removeDriver")
    public ResponseEntity<Void> removeCarFromDriver(@PathVariable Long carId, @PathVariable Long driverId) {
        carService.removeCarFromDriver(carId,driverId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable Long carId) {
        Car car = carService.getCar(carId);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long carId, @RequestBody CarRequestDTO carRequestDTO) {
        CarResponseDTO carResponseDTO = carService.updateCar(carId, carRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(carResponseDTO);
    }
}

