package com.example.sns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnsNotification {
    
    private String Type;
    private String MessageId;
    private String TopicArn;
    private String Subject;
    private String Message;
    private String Timestamp;
    private String SignatureVersion;
    private String Signature;
    private String SigningCertURL;
    private String UnsubscribeURL;
    
    @JsonProperty("SubscribeURL")
    private String subscribeURL;
    
    // Manually added getters
    public String getType() {
        return Type;
    }
    
    public String getMessage() {
        return Message;
    }
    
    public String getSubscribeURL() {
        return subscribeURL;
    }
}
