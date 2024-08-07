package com.project.CarPro.services;

import com.project.CarPro.dto.request.CarRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.CarResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.mapper.CarMapper;
import com.project.CarPro.model.*;
import com.project.CarPro.repositories.CarDriverRepository;
import com.project.CarPro.repositories.CarRepository;
import com.project.CarPro.repositories.DriverRepository;
import com.project.CarPro.repositories.FleetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public CarResponseDTO addCarToDriver(Long driverId, CarRequestDTO carRequestDTO, Long fleetId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        CarType carType = carRequestDTO.getCarType();
        //verific daca este dat fleetId si daca carType este electric
        if (fleetId != null && carType != CarType.ELECTRIC) {
            throw new RuntimeException("Only electric car type is allowed for fleet cars");
        }

        //TODO
        // de acceptat ca parametru carId
        // si de cautat in baza de date acea masina deja existenta
        Car car = new Car();
        car.setBrand(carRequestDTO.getBrand());
        car.setModel(carRequestDTO.getModel());
        car.setProductionYear(carRequestDTO.getProductionYear());
        car.setColor(carRequestDTO.getColor());
        car.setMileage(carRequestDTO.getMileage());
        car.setRegistrationNumber(carRequestDTO.getRegistrationNumber());
        car.setCarType(carRequestDTO.getCarType());
        if (fleetId != null) {
            Fleet fleet = fleetRepository.findById(fleetId)
                    .orElseThrow(() -> new RuntimeException("Fleet not found"));
            car.setFleet(fleet);
        }


        // Asociaz masina cu soferul
        CarDriver carDriver = new CarDriver();
        carDriver.setCar(car);
        carDriver.setDriver(driver);
        carDriver.setFleetId(fleetId);
        carDriver.setStartDate(LocalDate.now());


        List<CarDriver> carDriverList = driver.getCarDriverList();
        carDriverList.add(carDriver);
        driver.setCarDriverList(carDriverList);


        carRepository.save(car);
        CarResponseDTO carResponseDTO = carMapper.mapFromCarToCarResponseDTO(car);
        return carResponseDTO;

    }

    public boolean existsDriverOnCar(Long carId, Long driverId) {
        List<CarDriver> carDrivers = carDriverRepository.findByCar_Id(carId);
        return carDrivers.stream()
                .anyMatch(carDriver -> carDriver.getDriver().equals(driverId) && carDriver.getEndDate() == null);
    }
    //TODO
    //removeCarFromDriver(endDate)
    @Transactional
    public void removeCarFromDriver(Long carId, Long driverId) {
        List<CarDriver> carDrivers = carDriverRepository.findByCar_IdAndDriver_Id(carId,driverId);
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
