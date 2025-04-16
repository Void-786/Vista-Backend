package com.project.car_rental_services.controller;

import com.project.car_rental_services.modal.BookingDto;
import com.project.car_rental_services.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> bookPackage(@RequestBody BookingDto booking) {
        bookingService.sendBookingEmail(booking);
        return ResponseEntity.ok("Booking request submitted successfully!");
    }
}
