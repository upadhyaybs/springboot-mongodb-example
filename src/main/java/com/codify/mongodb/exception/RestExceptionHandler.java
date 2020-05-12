package com.codify.mongodb.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codify.mongodb.constant.APIStatusCode;
import com.codify.mongodb.domain.ErrorInfo;
import com.codify.mongodb.util.Response;
import com.codify.mongodb.util.TrackingIDGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author upadhyaybs
 *
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public @ResponseBody ResponseEntity<Response<ErrorInfo>> handleResourceNotFoundException(HttpServletRequest request,
			ResourceNotFoundException enfe) {
		String trackingId = TrackingIDGenerator.getTrackingID();
		log.error("Request raised a ResourceNotFoundException. trackingId :" + trackingId, enfe);
		return new ResponseEntity<>(
				Response.<ErrorInfo>builder().data(new ErrorInfo(trackingId, request.getRequestURI(), enfe))
						.status(APIStatusCode.RESOURCE_NOT_FOUND).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody ResponseEntity<Response<ErrorInfo>> handleIllegalArgumentException(HttpServletRequest request,
			IllegalArgumentException ex) {
		String trackingId = TrackingIDGenerator.getTrackingID();
		log.error("Request raised a IllegalArgumentException.trackingId " + trackingId, ex);
		return new ResponseEntity<>(
				Response.<ErrorInfo>builder().data(new ErrorInfo(trackingId, request.getRequestURI(), ex))
						.status(APIStatusCode.INVALID_FILTERS).build(),
				HttpStatus.BAD_REQUEST);
	}

}
