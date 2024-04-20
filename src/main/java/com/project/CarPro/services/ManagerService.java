package com.project.CarPro.services;


import com.project.CarPro.dto.ManagerRequestDTO;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.model.Manager;
import com.project.CarPro.repositories.FleetRepository;
import com.project.CarPro.repositories.ManagerRepository;
import com.project.CarPro.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    ManagerRepository managerRepository;
    UserRepository userRepository;
    FleetRepository fleetRepository;
@Autowired
    public ManagerService(ManagerRepository managerRepository, UserRepository userRepository, FleetRepository fleetRepository) {
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
        this.fleetRepository = fleetRepository;
    }
@Transactional
    public Manager addManager(ManagerRequestDTO managerRequestDTO){
        Manager manager = new Manager();
        manager.setName(managerRequestDTO.getName());
        manager.setEmail(managerRequestDTO.getEmail());
        manager.setTelephone(managerRequestDTO.getTelephone());
        manager.setPassword(managerRequestDTO.getPassword());
        return managerRepository.save(manager);
    }
@Transactional
    public Manager getManager(Long managerId) {
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
    }
}
