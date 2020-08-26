package com.revtaroom.apis.opencage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.web.client.RestTemplate;

import com.revtaroom.entities.Address;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @NoArgsConstructor
public class OpenCageClient {
	
	private String apiUrl;
	private RestTemplate restTemplate;
	
	public Geometry getCoordinates(Address addr) {
		
		String url = prepareUrl(addr);
		
		OpenCageResponse res = restTemplate.getForObject(url, OpenCageResponse.class);
		
		if(res.status.code >= 200 && res.status.code < 300) {
			return res.results[0].geometry;
		}
		else {
			System.out.println("Error in request: " + res.status.message);
		}
		
		return null;
	}
	
	private String prepareUrl(Address addr) {
		
		String addrString = stringifyAddress(addr);
		
		System.out.println("Url to API before encoding: " + addrString);
		
		String encodedAddr = encodeAddressString(addrString);
		
		String url = apiUrl + encodedAddr;
		
		return url;
	}
	
	private String stringifyAddress(Address addr) {
		
		StringBuilder sb = new StringBuilder();
		// Street Address
		sb.append(addr.getStreetAddr1());
		if(addr.getStreetAddr2() != null && !addr.getStreetAddr2().equals("")) {
			sb.append(addr.getStreetAddr2());
		}
		
		// House Number
		if(addr.getHouseNumber() != null && !addr.getHouseNumber().equals("")) {
			sb.append(", ").append(addr.getHouseNumber());
		}
		
		// Zip, city, state
		sb.append(", ").append(addr.getZip());
		sb.append(", ").append(addr.getCity()).append(", ").append(addr.getState());
		
		// Country
		if(addr.getCountry() != null && !addr.getCountry().equals("")) {
			sb.append(", ").append(addr.getCountry());
		}
		else {
			sb.append(", ").append("United States");
		}
		
		return sb.toString();
	}
	
	private String encodeAddressString(String addr) throws RuntimeException {
		String encodedAddr = "";
		
		try {
			
			encodedAddr = URLEncoder.encode(addr, StandardCharsets.UTF_8.toString());
			
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("Error encoding Url");
		}
		
		return encodedAddr;
		
	}
	
}
