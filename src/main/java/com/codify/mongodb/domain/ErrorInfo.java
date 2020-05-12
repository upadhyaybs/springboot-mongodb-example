package com.codify.mongodb.domain;

import lombok.Getter;

/**
 * @author upadhyaybs
 *
 */
@Getter
public class ErrorInfo {

    public final String trackingId;
    public final String url;
    public final String error;
    
    public ErrorInfo(String trackingId,String url, Exception ex) {
    	this.trackingId=trackingId;
        this.url = url;
        this.error = ex.getLocalizedMessage();
    }
	
}
