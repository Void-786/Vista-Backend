package com.project.car_rental_services.controller;

import com.project.car_rental_services.modal.ContactDto;
import com.project.car_rental_services.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitContact(@RequestBody ContactDto contactDto) {
        try {
            contactService.submitContactForm(contactDto);
            return ResponseEntity.ok("Contact form submitted successfully and email sent!");

        } catch (Exception e) {
            return new ResponseEntity<>("Error submitting contact form: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
