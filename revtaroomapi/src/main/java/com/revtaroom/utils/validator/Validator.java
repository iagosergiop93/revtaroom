package com.revtaroom.utils.validator;

public interface Validator<T> {
	
	void validate(T obj) throws ValidationException;
}
