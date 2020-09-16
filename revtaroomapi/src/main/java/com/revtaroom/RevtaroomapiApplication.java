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
import com.revtaroom.apis.opencage.utils.RestTemplateWrapper;
import com.revtaroom.security.jwt.JwtConfig;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.revtaroom.repositories")
@EnableAsync
@EnableSwagger2
public class RevtaroomapiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RevtaroomapiApplication.class, args);
	}
	
	@Bean
	public Docket apiDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
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
		RestTemplateWrapper rtw = new RestTemplateWrapper(restTemplate);
		OpenCageClient occ = new OpenCageClient(endpoint, rtw);
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
