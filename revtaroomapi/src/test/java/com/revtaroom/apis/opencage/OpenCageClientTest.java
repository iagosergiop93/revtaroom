package com.revtaroom.apis.opencage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class OpenCageClientTest {
	
	private OpenCageClient occ;
	
	@BeforeAll
	public void initAll() {
		occ = occ = new OpenCageClient();
		occ.setRestTemplate(null);
	}
	
}
