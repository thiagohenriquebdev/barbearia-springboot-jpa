package com.salaoAPI.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salaoAPI.services.exceptions.DataBaseException;
import com.salaoAPI.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler (ResourceNotFoundException.class) 
	public ResponseEntity<StandardError> resourceNotFound (ResourceNotFoundException e , HttpServletRequest request) {
		String error = "Resource not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler (DataBaseException.class) 
	public ResponseEntity<StandardError> database (DataBaseException e , HttpServletRequest request) {
		String error = "data base error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> generic(Exception e, HttpServletRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	    StandardError err = new StandardError(Instant.now(), status.value(),
	            e.getClass().getSimpleName(), e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(err);
	}
}
