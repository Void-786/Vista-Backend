package com.project.car_rental_services.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Service is up and running");
    }

    @GetMapping(value = "/actuator/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actuatorHealth() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");

        Map<String, Object> components = new HashMap<>();
        components.put("custom", Map.of("status", "UP"));

        response.put("components", components);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/actuator/health/readiness")
    public ResponseEntity<Map<String, Object>> readinessHealth() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/actuator/health/liveness")
    public ResponseEntity<Map<String, Object>> livenessHealth() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}
