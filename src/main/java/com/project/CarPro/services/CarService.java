package com.project.CarPro.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CarPro.dto.request.CarRequestDTO;
import com.project.CarPro.dto.response.CarResponseDTO;
import com.project.CarPro.mapper.CarMapper;
import com.project.CarPro.model.*;
import com.project.CarPro.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    CarRepository carRepository;
    FleetRepository fleetRepository;
    DriverRepository driverRepository;
    CarDriverRepository carDriverRepository;
    CarMapper carMapper;



    @Autowired
    public CarService(CarRepository carRepository, FleetRepository fleetRepository, DriverRepository driverRepository, CarDriverRepository carDriverRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.fleetRepository = fleetRepository;
        this.driverRepository = driverRepository;
        this.carDriverRepository = carDriverRepository;
        this.carMapper = carMapper;

    }



@Transactional
public CarResponseDTO addCar(CarRequestDTO carRequestDTO, Long fleetId) {

    //TODO
    // de acceptat ca parametru carId
    // si de cautat in baza de date acea masina deja existenta


    CarType carType = carRequestDTO.getCarType();
    //verific daca este dat fleetId si daca carType este electric
    if (fleetId != null && carType != CarType.ELECTRIC) {
        throw new RuntimeException("Only electric car type is allowed for fleet cars");
    }

    Car car = new Car();
    car.setBrand(carRequestDTO.getBrand());
    car.setModel(carRequestDTO.getModel());
    car.setColor(carRequestDTO.getColor());
    car.setProductionYear(carRequestDTO.getProductionYear());
    car.setMileage(carRequestDTO.getMileage());
    car.setRegistrationNumber(carRequestDTO.getRegistrationNumber());
    car.setCarType(carRequestDTO.getCarType());
    car.setVin(carRequestDTO.getVin());


    if (fleetId != null) {
        Fleet fleet = fleetRepository.findById(fleetId)
                .orElseThrow(() -> new RuntimeException("Fleet not found"));
        car.setFleet(fleet);
    }

    carRepository.save(car);
    return carMapper.mapFromCarToCarResponseDTO(car);
}

public CarResponseDTO addCarToDriver(Long driverId, Long carId) {
    Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
    Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
    Long fleetId = null;
    if (car.getFleet() != null) {
        fleetId = car.getFleet().getId();
    }

    // Asociaz masina cu soferul
    CarDriver carDriver = new CarDriver();
    carDriver.setCar(car);
    carDriver.setDriver(driver);
    carDriver.setFleetId(fleetId);
    carDriver.setStartDate(LocalDate.now());

    driver.getCarDriverList().add(carDriver);
    driverRepository.save(driver);
    return carMapper.mapFromCarToCarResponseDTO(car);
}


public boolean existsDriverOnCar(Long carId, Long driverId) {
    List<CarDriver> carDrivers = carDriverRepository.findByCar_Id(carId);
    return carDrivers.stream()
            .anyMatch(carDriver -> carDriver.getDriver().getId().equals(driverId) && carDriver.getEndDate() == null);
}

public boolean updateEndDate(Long carDriverId, LocalDate endDate) {
    Optional<CarDriver> optionalCarDriver = carDriverRepository.findById(carDriverId);
    if (optionalCarDriver.isPresent()) {
        CarDriver carDriver = optionalCarDriver.get();
        carDriver.setEndDate(endDate);
        carDriverRepository.save(carDriver);
        return true;
    } else {
        return false;
    }
}

//TODO
//removeCarFromDriver(endDate)
@Transactional
public void removeCarFromDriver(Long carId, Long driverId) {
    List<CarDriver> carDrivers = carDriverRepository.findByCar_IdAndDriver_Id(carId, driverId);
    Optional<CarDriver> carDriverOptional = carDrivers.stream()
            .filter(carDriver -> carDriver.getEndDate() == null)
            .findFirst();

    carDriverOptional.ifPresent(carDriver -> {
        carDriver.setEndDate(LocalDate.now()); // Remove the car
        carDriverRepository.save(carDriver);
    });
}

public void deleteCar(Long carId) {

    Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));

    List<CarDriver> carDrivers = carDriverRepository.findByCar(car);

    // sterg legaturile din car_driver
    carDriverRepository.deleteAll(carDrivers);
    carRepository.delete(car);
}

public Car getCar(Long carId) {
    return carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));
}

@Transactional
public CarResponseDTO updateCar(Long carId, CarRequestDTO carRequestDTO) {
    Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));
    Fleet carFleet = car.getFleet();
    if (carFleet != null && carRequestDTO.getCarType() != CarType.ELECTRIC) {
        throw new RuntimeException("Only electric car type is allowed for fleet cars");
    }

    car.setBrand(carRequestDTO.getBrand());
    car.setModel(carRequestDTO.getModel());
    car.setProductionYear(carRequestDTO.getProductionYear());
    car.setColor(carRequestDTO.getColor());
    car.setMileage(carRequestDTO.getMileage());
    car.setRegistrationNumber(carRequestDTO.getRegistrationNumber());
    car.setCarType(carRequestDTO.getCarType());

    carRepository.save(car);
    return carMapper.mapFromCarToCarResponseDTO(car);
}


}
