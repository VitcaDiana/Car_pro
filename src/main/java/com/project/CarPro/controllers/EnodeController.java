package com.project.CarPro.controllers;

import com.project.CarPro.services.EnodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class EnodeController {

    private final EnodeService enodeService;

    public EnodeController(EnodeService enodeService) {
        this.enodeService = enodeService;
    }

    @GetMapping("/link/{userId}")
    public String linkUser(@PathVariable String userId) {
        System.out.println("here");
        return enodeService.createLinkSession(userId);
    }

    @GetMapping("/link/callback")
    public String linkCallback(@RequestParam String code, @RequestParam String state) {
        // Handle the callback logic here
        return "Linking process completed with code: " + code + " and state: " + state;
    }


    @GetMapping("/{userId}/locations")
    public Mono<String> listLocations(
            @PathVariable String userId,
            @RequestParam(required = false) String after,
            @RequestParam(required = false) String before,
            @RequestParam(required = false) Integer pageSize) {
        return enodeService.listLocations(userId, after, before, pageSize);
    }

    @GetMapping("/state/{id}")
    public Mono<String> getVehicleState(@PathVariable String id) {
        return enodeService.getVehicleState(id);
    }

    @GetMapping("/user/vehicles/{userId}")
    public Mono<String> getUserVehicles(@PathVariable String userId) {
        return enodeService.getUserVehicles(userId);
    }

    @PostMapping("/enode")
    public ResponseEntity<String> syncronizeCarsWithEnode(){
        try {
            enodeService.fetchAndUpdateCars();
            return ResponseEntity.ok("Cars syncronized with enode API");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }






























}
