package com.project.CarPro.mapper;

import com.project.CarPro.dto.response.DriverResponseDTO;
import com.project.CarPro.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public DriverResponseDTO mapFromDriverToDriverResponseDTO(Driver driver) {
        DriverResponseDTO driverResponseDTO = new DriverResponseDTO();
        driverResponseDTO.setId(driver.getId());
        driverResponseDTO.setName(driver.getName());
        driverResponseDTO.setEmail(driver.getEmail());
        driverResponseDTO.setTelephone(driver.getTelephone());
        return driverResponseDTO;
    }
}
