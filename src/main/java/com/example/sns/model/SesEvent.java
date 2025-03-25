package com.example.sns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SesEvent {
    
    private String eventType;
    private Mail mail;
    private List<Bounce> bounce;
    private List<Complaint> complaint;
    private Delivery delivery;
    
    // Manually added getter for eventType
    public String getEventType() {
        return eventType;
    }
    
    // Manually added setter for eventType
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    // Manually added getter for mail
    public Mail getMail() {
        return mail;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Mail {
        private String timestamp;
        private String messageId;
        private String source;
        private List<String> destination;
        private Map<String, String> commonHeaders;
        private Map<String, String> headers;
        
        // Manually added getter for messageId
        public String getMessageId() {
            return messageId;
        }
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bounce {
        private String bounceType;
        private String bounceSubType;
        private List<Recipient> bouncedRecipients;
        private String timestamp;
        private String feedbackId;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Complaint {
        private List<Recipient> complainedRecipients;
        private String timestamp;
        private String feedbackId;
        private String complaintSubType;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Delivery {
        private String timestamp;
        private Integer processingTimeMillis;
        private List<String> recipients;
        private String smtpResponse;
        private String reportingMTA;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Recipient {
        private String emailAddress;
        private String action;
        private String status;
        private String diagnosticCode;
    }
}
