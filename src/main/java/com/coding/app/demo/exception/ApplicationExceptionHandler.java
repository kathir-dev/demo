package com.coding.app.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webReq) {
		ExceptionResponse resp = new ExceptionResponse(new Date(), ex.getMessage(), webReq.getDescription(Boolean.FALSE));
		return new ResponseEntity<Object> (resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PersonNotFoundException.class)
	public final ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException pE, WebRequest webReq) {
		ExceptionResponse resp = new ExceptionResponse(new Date(), pE.getMessage(), webReq.getDescription(Boolean.FALSE));
		return new ResponseEntity<Object> (resp, HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders header, 
			HttpStatus status, WebRequest request) {
		ExceptionResponse resp = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(resp, HttpStatus.BAD_REQUEST);
	}
	
}
