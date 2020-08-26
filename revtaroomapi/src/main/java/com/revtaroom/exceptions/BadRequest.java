package com.revtaroom.exceptions;

public class BadRequest extends BaseException {
	
	public BadRequest(int code, String msg) {
		super(code, msg);
	}
	
}
