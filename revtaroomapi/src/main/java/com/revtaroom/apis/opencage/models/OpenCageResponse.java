package com.revtaroom.apis.opencage.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties()
public class OpenCageResponse {
	
	@JsonProperty(value = "results")
	public Result[] results;
	
	@JsonProperty(value = "status")
	public Status status;
}
