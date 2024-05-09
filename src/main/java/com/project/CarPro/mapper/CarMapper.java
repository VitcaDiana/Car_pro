package com.project.CarPro.mapper;

import com.project.CarPro.dto.response.CarResponseDTO;
import com.project.CarPro.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarResponseDTO mapFromCarToCarResponseDTO(Car car) {
        CarResponseDTO carResponseDTO = new CarResponseDTO();
        carResponseDTO.setId(car.getId());
        carResponseDTO.setBrand(car.getBrand());
        carResponseDTO.setModel(car.getModel());
        carResponseDTO.setProductionYear(car.getProductionYear());
        carResponseDTO.setColor(car.getColor());
        carResponseDTO.setMileage(car.getMileage());
        carResponseDTO.setRegistrationNumber(car.getRegistrationNumber());
        carResponseDTO.setCarType(car.getCarType());

        return carResponseDTO;
    }
}
