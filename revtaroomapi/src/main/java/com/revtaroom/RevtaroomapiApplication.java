package com.revtaroom;

import java.util.concurrent.Executor;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.revtaroom.apis.opencage.OpenCageClient;
import com.revtaroom.security.jwt.JwtConfig;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.revtaroom.repositories")
public class RevtaroomapiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RevtaroomapiApplication.class, args);
	}
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("RestCalls-");
		executor.initialize();
		return executor;
	}
	
	@Value("${opencageapi.endpoint}")
	private String endpoint;
	
	@Bean
	public OpenCageClient openCageClient(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		OpenCageClient occ = new OpenCageClient();
		occ.setRestTemplate(restTemplate);
		occ.setApiUrl(endpoint);
		
		return occ;
	}
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Bean
	public JwtConfig jwtConfig() {
		JwtConfig config = new JwtConfig();
		byte[] secretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
		config.SIGNING_KEY = new SecretKeySpec(secretBytes, config.signatureAlg.getJcaName());
		
		return config;
	}

}
