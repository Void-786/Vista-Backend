package com.project.car_rental_services.controller;

import com.project.car_rental_services.service.QueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/query")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/submit-query")
    public ResponseEntity<?> submitQuery(@RequestBody Map<String, Object> formData) {
        try {
            queryService.submitQuery(formData);
            return ResponseEntity.ok("Query submitted successfully!");
        } catch (Exception e) {
            return new ResponseEntity<>("Error submitting query: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
