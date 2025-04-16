package com.project.car_rental_services.service;

import com.project.car_rental_services.modal.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${admin.email}")
    private String adminEmail;

    public void sendBookingEmail(BookingDto booking) {
        String subject = "New Booking Request " + booking.getFullName();
        String message = "Booking Details:\n\n"
                + "Full Name: " + booking.getFullName() + "\n"
                + "Mobile: " + booking.getMobileNumber() + "\n"
                + "Email: " + booking.getEmail() + "\n"
                + "Start Date: " + booking.getStartDate() + "\n"
                + "Place Name: " + booking.getPlaceName() + "\n"
                + "Package Name: " + booking.getPackageName() + "\n"
                + "Package price: " + booking.getPackagePrice() + "\n"
                + "Include Accommodations: " + (booking.isIncludeAccommodations() ? "Yes" : "No");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        String[] recipients = {adminEmail, booking.getEmail()};
        mailMessage.setTo(recipients);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
