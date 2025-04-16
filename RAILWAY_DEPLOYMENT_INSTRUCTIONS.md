# Railway Deployment Instructions

This document provides instructions for deploying the Vista Tours and Travels backend application to Railway.

## Prerequisites

1. A Railway account
2. MySQL database service on Railway (or external)
3. SMTP mail server credentials

## Deployment Steps

1. Fork or clone this repository to your GitHub account.
2. Connect your GitHub repository to Railway.
3. Add the MySQL plugin to your Railway project.
4. Set up the required environment variables (see below).
5. Deploy the application.

## Required Environment Variables

Set the following environment variables in your Railway project settings:

### Database Configuration
- `SPRING_DATASOURCE_USERNAME`: MySQL database username
- `SPRING_DATASOURCE_PASSWORD`: MySQL database password
- `MYSQLDATABASE`: MySQL database name
- `MYSQLHOST`: MySQL host (automatically set by Railway if using their MySQL plugin)
- `MYSQLPORT`: MySQL port (automatically set by Railway if using their MySQL plugin)

### Mail Configuration
- `SPRING_MAIL_HOST`: SMTP mail server host
- `SPRING_MAIL_PORT`: SMTP mail server port
- `SPRING_MAIL_USERNAME`: SMTP mail server username
- `SPRING_MAIL_PASSWORD`: SMTP mail server password

## Troubleshooting

If you encounter issues during deployment:

1. Check that all required environment variables are set correctly.
2. Verify that the MySQL database is accessible from the application.
3. Check the application logs for specific error messages.
4. Ensure your Railway account has sufficient resources for the application.

## Notes

- The application uses Java 21, which is specified in the `system.properties` file.
- The application uses Spring Boot 3.2.3.
- The application exposes port 8807, but Railway will automatically route traffic to this port.
