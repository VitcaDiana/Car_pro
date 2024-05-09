package com.project.CarPro.controllers;


import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
import com.project.CarPro.model.Manager;
import com.project.CarPro.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;
@Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    @PostMapping("/create")
    public ResponseEntity<ManagerResponseDTO> addManager (@RequestBody ManagerRequestDTO managerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.addManager(managerRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponseDTO> getManager(@PathVariable Long id) {
        ManagerResponseDTO manager = managerService.getManager(id);
        return ResponseEntity.status(HttpStatus.OK).body(manager);
    }
    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long managerId) {
        managerService.deleteManager(managerId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{managerId}")
    public ResponseEntity<ManagerResponseDTO> updateManager(@PathVariable Long managerId, @RequestBody ManagerRequestDTO managerRequestDTO) {
    ManagerResponseDTO managerResponseDTO = managerService.updateManager(managerId, managerRequestDTO);
    return ResponseEntity.status(HttpStatus.OK).body(managerResponseDTO);
    }
}
