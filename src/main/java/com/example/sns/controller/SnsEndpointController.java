package com.example.sns.controller;

import com.example.sns.model.SnsNotification;
import com.example.sns.service.SesEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/sns")
public class SnsEndpointController {

    private static final Logger log = LoggerFactory.getLogger(SnsEndpointController.class);
    
    private final SesEventService sesEventService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SnsEndpointController(SesEventService sesEventService, ObjectMapper objectMapper) {
        this.sesEventService = sesEventService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/endpoint")
    public ResponseEntity<String> handleSnsNotification(@RequestBody String message) {
        log.info("Received SNS notification: {}", message);
        
        try {
            SnsNotification notification = objectMapper.readValue(message, SnsNotification.class);
            log.debug("Parsed notification: {}", notification);
            
            // Handle SNS subscription confirmation
            if ("SubscriptionConfirmation".equals(notification.getType())) {
                log.info("Received subscription confirmation request: {}", notification.getSubscribeURL());
                sesEventService.confirmSubscription(notification.getSubscribeURL());
                return ResponseEntity.ok("Subscription confirmed");
            }
            
            // Handle SNS notification (SES event)
            if ("Notification".equals(notification.getType())) {
                sesEventService.processSesEvent(notification.getMessage());
                return ResponseEntity.ok("SES event processed successfully");
            }
            
            return ResponseEntity.ok("Message received but not processed");
        } catch (IOException e) {
            log.error("Error processing SNS notification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing notification");
        }
    }
}
