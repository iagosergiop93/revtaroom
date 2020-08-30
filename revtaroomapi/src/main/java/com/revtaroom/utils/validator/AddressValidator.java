package com.revtaroom.utils.validator;

import com.revtaroom.entities.Address;
import com.revtaroom.exceptions.BadRequest;

public class AddressValidator {

	public static void validate(Address obj) throws RuntimeException {
		if(obj == null || 
				obj.getStreetAddr1() == null || obj.getStreetAddr1().equals("") ||
				obj.getCity() == null || obj.getCity().equals("") ||
				obj.getState() == null || obj.getState().equals("")) {
			throw new BadRequest(400, "Invalid Address");
		}
	}

}
