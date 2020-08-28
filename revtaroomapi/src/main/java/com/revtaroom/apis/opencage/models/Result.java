package com.revtaroom.apis.opencage.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Result {
	
	@JsonProperty("geometry")
	public Geometry geometry;
	
}
