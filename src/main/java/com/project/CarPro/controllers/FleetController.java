package com.project.CarPro.controllers;

import com.project.CarPro.dto.request.FleetRequestDTO;
import com.project.CarPro.dto.request.ManagerRequestDTO;
import com.project.CarPro.dto.response.FleetResponseDTO;
import com.project.CarPro.dto.response.ManagerResponseDTO;
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
    public ResponseEntity<FleetResponseDTO> addFleet (@PathVariable Long managerId, @RequestBody FleetRequestDTO fleetRequestDTO ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fleetService.addFleetToManager(managerId,fleetRequestDTO));
    }
    @GetMapping("/{fleetId}")
    public ResponseEntity<FleetResponseDTO> getFleet(@PathVariable Long fleetId) {
        FleetResponseDTO fleet = fleetService.getFleet(fleetId);
        return ResponseEntity.status(HttpStatus.OK).body(fleet);
    }
    @DeleteMapping("/{fleetId}")
    public ResponseEntity<Void> deleteFleet(@PathVariable Long fleetId) {
        fleetService.deleteFleet(fleetId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{fleetId}")
    public ResponseEntity<FleetResponseDTO> updateFleet(@PathVariable Long fleetId, @RequestBody FleetRequestDTO fleetRequestDTO,@RequestParam(required = false) Long newManagerId) {
        FleetResponseDTO fleetResponseDTO = fleetService.updateFleet(fleetId, fleetRequestDTO,newManagerId);
        return ResponseEntity.status(HttpStatus.OK).body(fleetResponseDTO);
    }
}
