package com.revtaroom.apis.opencage.utils;

import org.springframework.web.client.RestTemplate;

import com.revtaroom.apis.opencage.models.OpenCageResponse;
import com.revtaroom.apis.opencage.models.RestClient;
import com.revtaroom.exceptions.ServerError;

public class RestTemplateWrapper implements RestClient<OpenCageResponse> {
	
	private RestTemplate restTemplate;
	
	public RestTemplateWrapper(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public OpenCageResponse get(String url) {
		OpenCageResponse res = restTemplate.getForObject(url, OpenCageResponse.class);
		return res;
	}
	
	public OpenCageResponse handleApiResponse(OpenCageResponse res) throws ServerError {
		if(res == null || (res.status.code < 200 || res.status.code > 299)) {
			throw new ServerError(500, res.status.message);
		}
		return res;
	}
	
}
