//package com.project.CarPro.controllers;
//
//
//import com.project.CarPro.services.EnodeApiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EnodeApiController {
//
//    private final EnodeApiService enodeApiService;
//
//    @Autowired
//    public EnodeApiController(EnodeApiService enodeApiService) {
//        this.enodeApiService = enodeApiService;
//    }
//
//    @GetMapping("/enode/user/{userId}")
//    public String getUserData(@PathVariable String userId) {
//        return enodeApiService.getUserData(userId);
//    }
//}
