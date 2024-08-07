package com.project.CarPro.controllers;

import com.project.CarPro.services.EnodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
