package com.project.CarPro.services;

import com.project.CarPro.dto.request.DriverRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.DriverResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.mapper.DriverMapper;
import com.project.CarPro.model.CarDriver;
import com.project.CarPro.model.Driver;
import com.project.CarPro.model.Manager;
import com.project.CarPro.repositories.CarDriverRepository;
import com.project.CarPro.repositories.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {
    DriverRepository driverRepository;
    DriverMapper driverMapper;
    CarDriverRepository carDriverRepository;

    @Autowired
    public DriverService(CarDriverRepository carDriverRepository, DriverMapper driverMapper, DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.carDriverRepository = carDriverRepository;
    }

    @Transactional
    public DriverResponseDTO addDriver(DriverRequestDTO driverRequestDTO) {
        Driver driver = new Driver();
        driver.setName(driverRequestDTO.getName());
        driver.setEmail(driverRequestDTO.getEmail());
        driver.setTelephone(driverRequestDTO.getTelephone());
        driver.setPassword(driverRequestDTO.getPassword());
        driverRepository.save(driver);
        DriverResponseDTO responseDTO = driverMapper.mapFromDriverToDriverResponseDTO(driver);
        return responseDTO;
    }

    public DriverResponseDTO getDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return driverMapper.mapFromDriverToDriverResponseDTO(driver);
    }

    public void deleteDriver(Long driverId) {
        driverRepository.deleteById(driverId);
    }

    public DriverResponseDTO updateDriver(Long driverId, DriverRequestDTO driverRequestDTO) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setName(driverRequestDTO.getName());
        driver.setEmail(driverRequestDTO.getEmail());
        driver.setTelephone(driverRequestDTO.getTelephone());
        driver.setPassword(driverRequestDTO.getPassword());
        driverRepository.save(driver);
        return driverMapper.mapFromDriverToDriverResponseDTO(driver);
    }

    public List<Driver> getAllDriversByCar(Long carId) {
        List<CarDriver> carDrivers = carDriverRepository.findByCar_Id(carId);

        return carDrivers.stream()
                .map(CarDriver::getDriver)
                .collect(Collectors.toList());
    }

    public List<Driver> getAllDrivers() {
        List<CarDriver> carDrivers = carDriverRepository.findAll();
        return carDrivers.stream()
                .map(CarDriver::getDriver)
                .collect(Collectors.toList());
    }
}




//    DriverResponseDTO mapFromDriverToDriverResponseDTO(Driver driver) {
//        DriverResponseDTO driverResponseDTO = new DriverResponseDTO();
//        driverResponseDTO.setId(driver.getId());
//        driverResponseDTO.setName(driver.getName());
//        driverResponseDTO.setEmail(driver.getEmail());
//        driverResponseDTO.setTelephone(driver.getTelephone());
//        return driverResponseDTO;
//    }


