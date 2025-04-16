package com.project.car_rental_services.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${admin.email}")
    private String adminEmail;

    public void submitQuery(Map<String, Object> formData) throws Exception {
        // 1. Data Extraction
        String startDate = (String) formData.get("startDate");
        String endDate = (String) formData.get("endDate");
        String travelersStr = (String) formData.get("travelers");
        String childCountStr = (String) formData.get("childrenCount");
        String tripType = (String) formData.get("tripType");
        List<String> cities = (List<String>) formData.get("cities");
        String firstName = (String) formData.get("firstName");
        String lastName = (String) formData.get("lastName");
        String email = (String) formData.get("email");
        String phone = (String) formData.get("phone");

        int travelers = 0;
        int childCount = 0;
        try {
            travelers = Integer.parseInt(travelersStr);
            childCount = Integer.parseInt(childCountStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number of travelers: " + travelersStr, e);
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(adminEmail);
        message.setTo(email);
        message.setSubject("New Query Form Submission");

        String cityString = (cities != null) ? String.join(", ", cities) : "N/A";
        message.setText(
                "New Query Form Submission:\n\n" +
                        "Start Date: " + startDate + "\n" +
                        "End Date: " + endDate + "\n" +
                        "Travelers: " + travelers + "\n" +
                        "Children: " + childCount + "\n" +
                        "Trip Type: " + tripType + "\n" +
                        "Cities: " + cityString + "\n\n" +
                        "Personal Details:\n" +
                        "First Name: " + firstName + "\n" +
                        "Last Name: " + lastName + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone
        );

        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new Exception("Error sending email", e);
        }
    }
}
