package com.codify.mongodb.util;


import com.codify.mongodb.constant.APIStatusCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author upadhyaybs
 *
 */
@Setter
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
	
	private T data;
	private APIStatusCode status;
	private String error;
	
}
