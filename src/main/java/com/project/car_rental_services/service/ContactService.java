package com.project.car_rental_services.service;

import com.project.car_rental_services.modal.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${admin.email}")
    private String adminEmail;

    public void submitContactForm(ContactDto contactDto) throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(adminEmail);
        message.setSubject("New Contact Form Submission");

        message.setText(
                "New Contact Form Submission:\n\n" +
                        "Name: " + contactDto.getName() + "\n" +
                        "Email: " + contactDto.getEmail() + "\n" +
                        "Phone: " + contactDto.getPhone() + "\n" +
                        "Message: " + contactDto.getMessage()
        );

        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new Exception("Error sending contact form email", e);
        }
    }
}
