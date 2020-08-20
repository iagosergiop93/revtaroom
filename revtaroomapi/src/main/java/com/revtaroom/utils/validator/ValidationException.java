package com.revtaroom.utils.validator;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ValidationException() {
		super();
	}
	
	public ValidationException(String msg) {
		super(msg);
	}

}
