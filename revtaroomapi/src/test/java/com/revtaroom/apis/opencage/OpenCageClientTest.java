package com.revtaroom.apis.opencage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import com.revtaroom.apis.opencage.models.Geometry;
import com.revtaroom.apis.opencage.models.OpenCageResponse;
import com.revtaroom.apis.opencage.models.RestClient;
import com.revtaroom.apis.opencage.models.Result;
import com.revtaroom.apis.opencage.models.Status;
import com.revtaroom.apis.opencage.utils.UrlAdapter;
import com.revtaroom.entities.Address;

@Testable
@TestInstance(Lifecycle.PER_CLASS)
public class OpenCageClientTest {
	
	private Geometry getValidGeometry() {
		Geometry geometry = new Geometry();
		geometry.lat = 10;
		geometry.lng = 15;
		
		return geometry;
	}
	
	private OpenCageResponse mockOkResponse(Geometry geometry) {
		Result res = new Result();
		res.geometry = geometry;
		Result[] resArr = { res };
		
		OpenCageResponse resp = new OpenCageResponse();
		resp.status = new Status();
		resp.status.code = 200;
		resp.results = resArr;
		
		return resp;
	}
	
	@Test
	public void handleOkApiResponse() {
		Geometry geometry = getValidGeometry();
		
		OpenCageResponse okResp = mockOkResponse(geometry);
		RestClient<OpenCageResponse> rc = (url) -> okResp;
		
		OpenCageClient occ = new OpenCageClient("", rc);
		
		Geometry finalResult = occ.getCoordinates(new Address());
		
		assertEquals(geometry, finalResult);
	}
	
}
