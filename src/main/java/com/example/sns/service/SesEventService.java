package com.example.sns.service;

import com.example.sns.model.SesEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SesEventService {

    private static final Logger log = LoggerFactory.getLogger(SesEventService.class);
    
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public SesEventService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Process SES event received from SNS
     * @param message The JSON message containing SES event data
     */
    public void processSesEvent(String message) {
        try {
            log.debug("Raw SES event message: {}", message);
            SesEvent sesEvent = objectMapper.readValue(message, SesEvent.class);
            log.info("Processing SES event: {}", sesEvent.getEventType());
            
            // Handle different types of SES events
            switch (sesEvent.getEventType()) {
                case "Bounce":
                    handleBounce(sesEvent);
                    break;
                case "Complaint":
                    handleComplaint(sesEvent);
                    break;
                case "Delivery":
                    handleDelivery(sesEvent);
                    break;
                case "Open":
                    handleOpen(sesEvent);
                    break;
                default:
                    log.info("Unhandled event type: {}", sesEvent.getEventType());
            }
        } catch (IOException e) {
            log.error("Error parsing SES event", e);
        }
    }

    /**
     * Confirm SNS subscription by making a GET request to the provided URL
     * @param subscribeUrl The URL to confirm subscription
     */
    public void confirmSubscription(String subscribeUrl) {
        log.info("Confirming subscription with URL: {}", subscribeUrl);
        try {
            String response = restTemplate.getForObject(subscribeUrl, String.class);
            log.info("Subscription confirmation response: {}", response);
        } catch (Exception e) {
            log.error("Error confirming subscription", e);
        }
    }

    private void handleBounce(SesEvent sesEvent) {
        log.info("Handling bounce event for message: {}", sesEvent.getMail().getMessageId());
        // Implement bounce handling logic here
        // For example, update a database, send notifications, etc.
    }

    private void handleComplaint(SesEvent sesEvent) {
        log.info("Handling complaint event for message: {}", sesEvent.getMail().getMessageId());
        // Implement complaint handling logic here
    }

    private void handleDelivery(SesEvent sesEvent) {
        log.info("Handling delivery event for message: {}", sesEvent.getMail().getMessageId());
        // Implement delivery handling logic here
    }
    
    private void handleOpen(SesEvent sesEvent) {
        log.info("Handling open event for message: {}", sesEvent.getMail().getMessageId());
        // Implement open event handling logic here
    }
}
