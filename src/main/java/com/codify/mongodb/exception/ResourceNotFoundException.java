package com.codify.mongodb.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @author upadhyaybs
 *
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
