package com.example.sns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnsNotification {
    
    @JsonProperty("Type")
    private String type;
    
    @JsonProperty("MessageId")
    private String messageId;
    
    @JsonProperty("TopicArn")
    private String topicArn;
    
    @JsonProperty("Subject")
    private String subject;
    
    @JsonProperty("Message")
    private String message;
    
    @JsonProperty("Timestamp")
    private String timestamp;
    
    @JsonProperty("SignatureVersion")
    private String signatureVersion;
    
    @JsonProperty("Signature")
    private String signature;
    
    @JsonProperty("SigningCertURL")
    private String signingCertURL;
    
    @JsonProperty("UnsubscribeURL")
    private String unsubscribeURL;
    
    @JsonProperty("SubscribeURL")
    private String subscribeURL;
    
    // No need for manual getters as @Data provides them
    // But keeping these for backward compatibility
    public String getType() {
        return type;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getSubscribeURL() {
        return subscribeURL;
    }
}
