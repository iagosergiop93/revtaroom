package com.revtaroom.apis.opencage.models;

public interface RestClient<T> {
	
	T get(String url) throws RuntimeException;
	
}
