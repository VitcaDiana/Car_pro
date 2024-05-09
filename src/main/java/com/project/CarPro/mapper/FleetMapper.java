package com.project.CarPro.mapper;

import com.project.CarPro.dto.response.FleetResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FleetMapper {

    @Autowired
    private ManagerMapper managerMapper;

    public FleetResponseDTO mapFromFleetToFleetResponseDTO(Fleet fleet) {
        FleetResponseDTO fleetResponseDTO = new FleetResponseDTO();
        fleetResponseDTO.setFleetName(fleet.getFleetName());
        fleetResponseDTO.setId(fleet.getId());
        if (fleet.getManager() != null) {
            ManagerResponseDTO managerResponseDTO = managerMapper.mapFromManagerToManagerResponseDTO(fleet.getManager());
            fleetResponseDTO.setManagerResponseDTO(managerResponseDTO);
        }
        return fleetResponseDTO;
    }
}
