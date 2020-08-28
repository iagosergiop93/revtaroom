package com.revtaroom.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BaseException extends RuntimeException {
	
	private Error error;
	
	public BaseException(int code, String message) {
		this.error = new Error(code, message);
	}
	
}

@Getter @Setter
class Error {
	private int code;
	private String message;
	
	public Error(int code, String msg) {
		this.code = code;
		this.message = msg;
	}
	
}