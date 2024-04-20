package com.project.CarPro.services;

import com.project.CarPro.dto.CarRequestDTO;
import com.project.CarPro.model.Car;
import com.project.CarPro.model.CarDriver;
import com.project.CarPro.model.Driver;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.repositories.CarRepository;
import com.project.CarPro.repositories.DriverRepository;
import com.project.CarPro.repositories.FleetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    CarRepository carRepository;
    FleetRepository fleetRepository;
    DriverRepository driverRepository;
@Autowired
    public CarService(CarRepository carRepository,FleetRepository fleetRepository,DriverRepository driverRepository) {
        this.carRepository = carRepository;
        this.fleetRepository = fleetRepository;
        this.driverRepository = driverRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Transactional
    public Car addCarToDriver(Long driverId, CarRequestDTO carRequestDTO, Long fleetId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Car car = new Car();
        car.setBrand(carRequestDTO.getBrand());
        car.setModel(carRequestDTO.getModel());
        car.setProductionYear(carRequestDTO.getProductionYear());
        car.setColor(carRequestDTO.getColor());
        car.setMileage(carRequestDTO.getMileage());
        car.setRegistrationNumber(carRequestDTO.getRegistrationNumber());
        car.setCarType(carRequestDTO.getCarType());

        // Asociază mașina cu șoferul
        CarDriver carDriver = new CarDriver();
        carDriver.setCar(car);
        carDriver.setDriver(driver);

        List<CarDriver> carDriverList = driver.getCarDriverList();
        carDriverList.add(carDriver);
        driver.setCarDriverList(carDriverList);

        // Dacă fleetId este furnizat, asociază mașina cu flota specificată
        if (fleetId != null) {
            Fleet fleet = fleetRepository.findById(fleetId)
                    .orElseThrow(() -> new RuntimeException("Fleet not found"));
            car.setFleet(fleet);
        }

        return carRepository.save(car);


    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}
