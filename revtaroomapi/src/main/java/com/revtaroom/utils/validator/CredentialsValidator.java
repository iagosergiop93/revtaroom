package com.revtaroom.utils.validator;

import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Credentials;
import com.revtaroom.exceptions.BadRequest;

@Component
public class CredentialsValidator {

	public static void validate(Credentials obj) throws BadRequest {
		if(obj.getUsername() == null || obj.getUsername().equals("") ||
				obj.getPasswd() == null || obj.getPasswd().equals("")) {
			
			throw new BadRequest(400, "Missing field");
		}
		
	}

}
