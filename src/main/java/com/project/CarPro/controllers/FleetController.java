package com.project.CarPro.controllers;

import com.project.CarPro.dto.FleetRequestDTO;
import com.project.CarPro.dto.ManagerRequestDTO;
import com.project.CarPro.model.Fleet;
import com.project.CarPro.model.Manager;
import com.project.CarPro.services.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fleet")
public class FleetController {
    FleetService fleetService;
@Autowired
    public FleetController(FleetService fleetService) {
        this.fleetService = fleetService;
    }
    @PostMapping("/create/{managerId}/")
    public ResponseEntity<Fleet> addFleet (@PathVariable Long managerId, @RequestBody FleetRequestDTO fleetRequestDTO ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fleetService.addFleetToManager(managerId,fleetRequestDTO));
    }
}
