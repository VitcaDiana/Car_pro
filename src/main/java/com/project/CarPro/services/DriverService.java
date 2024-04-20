package com.project.CarPro.services;

import com.project.CarPro.dto.DriverRequestDTO;
import com.project.CarPro.dto.ManagerRequestDTO;
import com.project.CarPro.model.Driver;
import com.project.CarPro.model.Manager;
import com.project.CarPro.repositories.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    DriverRepository driverRepository;
@Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Driver addDriver(DriverRequestDTO driverRequestDTO){
        Driver driver = new Driver();
        driver.setName(driverRequestDTO.getName());
        driver.setEmail(driverRequestDTO.getEmail());
        driver.setTelephone(driverRequestDTO.getTelephone());
        return driverRepository.save(driver);
    }
}
