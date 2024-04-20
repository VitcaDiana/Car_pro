package com.project.CarPro.controllers;


import com.project.CarPro.dto.ManagerRequestDTO;
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
    public ResponseEntity<Manager> addManager (@RequestBody ManagerRequestDTO managerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.addManager(managerRequestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManager(@PathVariable Long id) {
        Manager manager = managerService.getManager(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }
}
