package com.project.CarPro.mapper;

import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.model.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    public ManagerResponseDTO mapFromManagerToManagerResponseDTO(Manager manager) {
        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO();
        managerResponseDTO.setId(manager.getId());
        managerResponseDTO.setName(manager.getName());
        managerResponseDTO.setEmail(manager.getEmail());
        managerResponseDTO.setTelephone(manager.getTelephone());
        return managerResponseDTO;
    }
}
