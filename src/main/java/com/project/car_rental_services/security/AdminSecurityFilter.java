package com.project.car_rental_services.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdminSecurityFilter implements Filter {

    private final JwtUtil jwtUtil; // Inject JwtUtil for token validation

    public AdminSecurityFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // If URL contains "admin", validate JWT token
        if (requestURI.contains("admin")) {
            String authHeader = httpRequest.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization token required");
                return;
            }

            String token = authHeader.substring(7);

            try {
                jwtUtil.validateToken(token);  // Validate token using JwtUtil
            } catch (Exception e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
