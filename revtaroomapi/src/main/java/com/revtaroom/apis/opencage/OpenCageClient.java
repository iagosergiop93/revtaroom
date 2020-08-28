package com.revtaroom.apis.opencage;

import com.revtaroom.apis.opencage.models.Geometry;
import com.revtaroom.apis.opencage.models.OpenCageResponse;
import com.revtaroom.apis.opencage.models.RestClient;
import com.revtaroom.apis.opencage.utils.UrlAdapter;
import com.revtaroom.entities.Address;
import com.revtaroom.exceptions.ServerError;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class OpenCageClient {
	
	private String apiUrl;
	private RestClient<OpenCageResponse> restClient;
	
	public OpenCageClient(String apiUrl, RestClient<OpenCageResponse> restClient) {
		super();
		this.apiUrl = apiUrl;
		this.restClient = restClient;
	}
	
	public Geometry getCoordinates(Address addr) throws ServerError {

		String url = apiUrl + UrlAdapter.prepareUrl(addr);
		OpenCageResponse res = restClient.get(url);
		Geometry geometry = res.results[0].geometry;
		
		return geometry;
	}
	
}
