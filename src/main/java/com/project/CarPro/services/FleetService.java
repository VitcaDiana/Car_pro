package com.project.CarPro.services;

import com.project.CarPro.dto.FleetRequestDTO;
import com.project.CarPro.dto.ManagerRequestDTO;
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
@Autowired
    public FleetService(FleetRepository fleetRepository, ManagerRepository managerRepository) {
        this.fleetRepository = fleetRepository;
        this.managerRepository = managerRepository;
    }
    @Transactional
    public Fleet addFleetToManager(Long managerId, FleetRequestDTO fleetRequestDTO) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        Fleet fleet = new Fleet();
        fleet.setFleetName(fleetRequestDTO.getFleetName());
        fleet.setTotalNumberOfCars(fleetRequestDTO.getTotalNumberOfCars());

        manager.setFleet(fleet);
        fleet.setManager(manager);

        return fleetRepository.save(fleet);
    }
}
