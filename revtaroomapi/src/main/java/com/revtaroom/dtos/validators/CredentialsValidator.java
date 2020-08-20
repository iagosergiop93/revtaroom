package com.revtaroom.dtos.validators;

import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Credentials;
import com.revtaroom.utils.validator.ValidationException;
import com.revtaroom.utils.validator.Validator;

@Component
public class CredentialsValidator implements Validator<Credentials> {

	@Override
	public void validate(Credentials obj) throws ValidationException {
		if(obj.getUsername() == null || obj.getUsername().equals("") ||
				obj.getPasswd() == null || obj.getPasswd().equals("")) {
			
			throw new ValidationException("Missing field");
		}
		
	}

}
