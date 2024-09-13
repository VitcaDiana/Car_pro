package com.project.CarPro.controllers;

import com.project.CarPro.dto.request.CarRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.CarResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.model.Car;
import com.project.CarPro.model.CarDriver;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.repositories.CarDriverRepository;
import com.project.CarPro.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarDriverRepository carDriverRepository;
    CarService carService;

    @Autowired
    public CarController(CarService carService, CarDriverRepository carDriverRepository) {
        this.carService = carService;
        this.carDriverRepository = carDriverRepository;
    }


    @PostMapping("/")
    public ResponseEntity<CarResponseDTO> addCar(@RequestBody CarRequestDTO carRequestDTO, @RequestParam(required = false) Long fleetId) {
        CarResponseDTO carResponseDTO = carService.addCar(carRequestDTO, fleetId);
        return ResponseEntity.status(HttpStatus.CREATED).body(carResponseDTO);
    }

    @PostMapping("/{carId}/addCarToDriver/{driverId}")
    public ResponseEntity<CarResponseDTO> addCarToDriver(@PathVariable Long carId, @PathVariable Long driverId) {
        CarResponseDTO carResponseDTO = carService.addCarToDriver(driverId, carId);
        return ResponseEntity.ok(carResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{carId}/removeDriver")
    public ResponseEntity<Void> removeCarFromDriver(@PathVariable Long carId, @PathVariable Long driverId) {
        carService.removeCarFromDriver(carId, driverId);
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

    @GetMapping("/{carId}/{driverId}")
    public ResponseEntity<Boolean> existsDriverOnCar(@PathVariable Long carId, @PathVariable Long driverId) {
        return ResponseEntity.ok(carService.existsDriverOnCar(carId, driverId));
    }

    @PutMapping("/{carDriverId}/endDate")
    public ResponseEntity<String> updateEndDate(@PathVariable Long carDriverId, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        boolean updated = carService.updateEndDate(carDriverId, endDate);
        if (updated) {
            return ResponseEntity.ok("End date updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarDriver not found");
        }
    }
}

