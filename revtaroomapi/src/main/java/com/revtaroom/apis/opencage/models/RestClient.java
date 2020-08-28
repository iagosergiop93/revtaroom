package com.revtaroom.apis.opencage.models;

import com.revtaroom.exceptions.ServerError;

public interface RestClient<T> {
	
	T get(String url) throws ServerError;
	
}
