package com.neueda.payments.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class HealthCheckController {

    @GetMapping("/health")
    public Map<String, String> healthCheck(){
        return Map.of("status", "ok");
    }
}
