package com.project.CarPro.services;

import com.project.CarPro.dto.request.DriverRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.DriverResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.mapper.DriverMapper;
import com.project.CarPro.model.Driver;
import com.project.CarPro.model.Manager;
import com.project.CarPro.repositories.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    DriverRepository driverRepository;
    DriverMapper driverMapper;

    @Autowired
    public DriverService(DriverMapper driverMapper,DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
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


//    DriverResponseDTO mapFromDriverToDriverResponseDTO(Driver driver) {
//        DriverResponseDTO driverResponseDTO = new DriverResponseDTO();
//        driverResponseDTO.setId(driver.getId());
//        driverResponseDTO.setName(driver.getName());
//        driverResponseDTO.setEmail(driver.getEmail());
//        driverResponseDTO.setTelephone(driver.getTelephone());
//        return driverResponseDTO;
//    }

}
