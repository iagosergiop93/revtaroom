package com.revtaroom.apis.opencage.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.revtaroom.entities.Address;

public class UrlAdapter {
	
	public static String prepareUrl(Address addr) {
		
		String addrString = stringifyAddress(addr);
		
		String encodedAddr = encodeAddressString(addrString);
		
		return encodedAddr;
	}
	
	private static String stringifyAddress(Address addr) {
		
		StringBuilder sb = new StringBuilder();
		// Street Address
		sb.append(addr.getStreetAddr1());
		if(addr.getStreetAddr2() != null && !addr.getStreetAddr2().equals("")) {
			sb.append(" ").append(addr.getStreetAddr2());
		}
		
		// House Number
		if(addr.getHouseNumber() != null && !addr.getHouseNumber().equals("")) {
			sb.append(" ").append(addr.getHouseNumber());
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
	
	private static String encodeAddressString(String addr) throws RuntimeException {
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
