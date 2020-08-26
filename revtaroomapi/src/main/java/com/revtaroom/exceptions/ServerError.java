package com.revtaroom.exceptions;

public class ServerError extends BaseException {
	
	public ServerError(int code, String msg) {
		super(code, msg);
	}
	
}
