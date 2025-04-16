package com.project.car_rental_services.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        
        logger.info("Incoming request: {} {}", method, requestURI);
        
        // Log headers for health check endpoints
        if (requestURI.contains("/health") || requestURI.contains("/actuator")) {
            logger.info("Health check request headers:");
            httpRequest.getHeaderNames().asIterator().forEachRemaining(headerName -> 
                logger.info("  {}: {}", headerName, httpRequest.getHeader(headerName))
            );
        }
        
        try {
            chain.doFilter(request, response);
        } finally {
            int status = httpResponse.getStatus();
            logger.info("Response status for {} {}: {}", method, requestURI, status);
        }
    }
}