package com.project.CarPro.services;

import com.project.CarPro.dto.request.FleetRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.FleetResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.mapper.FleetMapper;
import com.project.CarPro.mapper.ManagerMapper;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.model.Manager;
import com.project.CarPro.repositories.FleetRepository;
import com.project.CarPro.repositories.ManagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FleetService {
    FleetRepository fleetRepository;
    ManagerRepository managerRepository;
    FleetMapper fleetMapper;
    ManagerMapper managerMapper;

@Autowired
    public FleetService(FleetMapper fleetMapper,ManagerMapper managerMapper, FleetRepository fleetRepository, ManagerRepository managerRepository) {
        this.fleetRepository = fleetRepository;
        this.managerRepository = managerRepository;
        this.fleetMapper=fleetMapper;
        this.managerMapper=managerMapper;
    }
    @Transactional
    public FleetResponseDTO addFleetToManager(Long managerId, FleetRequestDTO fleetRequestDTO) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        Fleet fleet = new Fleet();
        fleet.setFleetName(fleetRequestDTO.getFleetName());


        manager.setFleet(fleet);
        fleet.setManager(manager);
        fleetRepository.save(fleet);

        FleetResponseDTO fleetResponseDTO = fleetMapper.mapFromFleetToFleetResponseDTO(fleet);
        ManagerResponseDTO managerResponseDTO = managerMapper.mapFromManagerToManagerResponseDTO(manager);
        fleetResponseDTO.setManagerResponseDTO(managerResponseDTO);

        return fleetResponseDTO;
    }
    public void deleteFleet(Long fleetId) {
        fleetRepository.deleteById(fleetId);
    }


    public FleetResponseDTO getFleet(Long fleetId) {
        Fleet fleet = fleetRepository.findById(fleetId)
                .orElseThrow(() -> new RuntimeException("Fleet not found"));
        return fleetMapper.mapFromFleetToFleetResponseDTO(fleet);
    }
    @Transactional
    public FleetResponseDTO updateFleet(Long fleetId, FleetRequestDTO fleetRequestDTO,Long newManagerId) {
        Fleet fleet = fleetRepository.findById(fleetId)
                .orElseThrow(() -> new RuntimeException("Fleet not found"));

        // Actualizeaz numele flotei
        fleet.setFleetName(fleetRequestDTO.getFleetName());

        if (newManagerId != null) {
            Manager newManager = managerRepository.findById(newManagerId)
                    .orElseThrow(() -> new RuntimeException("Manager not found"));

            // Asociaz noul manager cu flota
            fleet.setManager(newManager);
        }

        fleetRepository.save(fleet);

        return fleetMapper.mapFromFleetToFleetResponseDTO(fleet);
    }



//    private FleetResponseDTO mapFromFleetToFleetResponseDTO(Fleet fleet) {
//       FleetResponseDTO fleetResponseDTO = new FleetResponseDTO();
//       fleetResponseDTO.setFleetName(fleet.getFleetName());
//       fleetResponseDTO.setId(fleet.getId());
//        ManagerService managerService = new ManagerService();
//        if (fleet.getManager() != null) {
//            ManagerResponseDTO managerResponseDTO = managerService.mapFromManagerToManagerResponseDTO(fleet.getManager());
//            fleetResponseDTO.setManagerResponseDTO(managerResponseDTO);
//        }
//        return fleetResponseDTO;
//    }

}
