package com.project.CarPro.services;


import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.mapper.ManagerMapper;
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
    ManagerMapper managerMapper;

    @Autowired
    public ManagerService(ManagerMapper managerMapper,ManagerRepository managerRepository, UserRepository userRepository, FleetRepository fleetRepository) {
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
        this.fleetRepository = fleetRepository;
        this.managerMapper = managerMapper;
    }



    @Transactional
    public ManagerResponseDTO addManager(ManagerRequestDTO managerRequestDTO) {
        Manager manager = new Manager();
        manager.setName(managerRequestDTO.getName());
        manager.setEmail(managerRequestDTO.getEmail());
        manager.setTelephone(managerRequestDTO.getTelephone());
        manager.setPassword(managerRequestDTO.getPassword());
        managerRepository.save(manager);
        ManagerResponseDTO managerResponseDTO = managerMapper.mapFromManagerToManagerResponseDTO(manager);
        return managerResponseDTO;
    }

    @Transactional
    public ManagerResponseDTO getManager(Long managerId) {
        Manager manager= managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found"));
         return managerMapper.mapFromManagerToManagerResponseDTO(manager);
    }

    public void deleteManager(Long managerId) {
        managerRepository.deleteById(managerId);
    }
    public ManagerResponseDTO updateManager(Long managerId, ManagerRequestDTO managerRequestDTO) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        manager.setName(managerRequestDTO.getName());
        manager.setEmail(managerRequestDTO.getEmail());
        manager.setTelephone(managerRequestDTO.getTelephone());
        manager.setPassword(managerRequestDTO.getPassword());
        managerRepository.save(manager);
        return managerMapper.mapFromManagerToManagerResponseDTO(manager);
    }


//    ManagerResponseDTO mapFromManagerToManagerResponseDTO(Manager manager) {
//        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO();
//        managerResponseDTO.setId(manager.getId());
//        managerResponseDTO.setName(manager.getName());
//        managerResponseDTO.setEmail(manager.getEmail());
//        managerResponseDTO.setTelephone(manager.getTelephone());
//        return managerResponseDTO;
//    }

}
