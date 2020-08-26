package com.revtaroom.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revtaroom.exceptions.BadRequest;
import com.revtaroom.exceptions.ServerError;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { BadRequest.class })
	public ResponseEntity<Object> handleBadRequests(BadRequest bre) {
		System.out.println("In Global Exception Handler");
		return ResponseEntity.badRequest().body(bre.getError());
	}
	
	@ExceptionHandler(value = { ServerError.class })
	public ResponseEntity<Object> handleServerError(ServerError se) {
		System.out.println("In Global Exception Handler");
		return ResponseEntity.badRequest().body(se.getError());
	}
	
}
