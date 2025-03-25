# SNS SpringBoot Application

This Spring Boot application serves as an HTTPS endpoint for Amazon SNS notifications, specifically handling SES (Simple Email Service) events.

## Features

- Receives and processes Amazon SNS notifications
- Handles SES event types (Bounce, Complaint, Delivery)
- Automatically confirms SNS subscription requests
- Built with Java 8 and Spring Boot 2.3.x

## Prerequisites

- Java 8 JDK
- Maven 3.x
- AWS account with SNS and SES configured

## Getting Started

### Building the Application

```bash
mvn clean package
```

### Running the Application

```bash
java -jar target/sns-springboot-0.0.1-SNAPSHOT.jar
```

Or using Maven:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

## Endpoint

The SNS endpoint is available at:

```
POST /api/sns/endpoint
```

## Configuring SNS

1. Create an SNS topic in your AWS account
2. Configure SES to publish events to this SNS topic
3. Create a subscription to your SNS topic with the protocol HTTPS and the endpoint URL pointing to your application's endpoint (e.g., https://your-domain.com/api/sns/endpoint)
4. The application will automatically confirm the subscription when it receives the confirmation request

## Customizing Event Handling

To customize how SES events are handled, modify the `SesEventService` class to implement your specific business logic for bounce, complaint, and delivery events.

## Security Considerations

For production use, consider implementing:

- HTTPS with a valid SSL certificate
- Authentication for the SNS endpoint
- SNS message signature verification
