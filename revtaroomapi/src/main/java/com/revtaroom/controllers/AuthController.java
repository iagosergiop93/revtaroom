package com.revtaroom.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revtaroom.dtos.Credentials;
import com.revtaroom.dtos.Principal;
import com.revtaroom.security.PrincipalEncoder;
import com.revtaroom.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrincipalEncoder principalEncoder;
	
	@ResponseStatus(code = HttpStatus.OK)
	@PostMapping()
	public Principal auth(HttpServletRequest req, HttpServletResponse resp, @RequestBody(required = false) Credentials cred) throws Throwable {
		
		Principal principal = null;
		
		if(cred != null) {
			principal = userService.login(cred);
		}
		else {
			String token = req.getHeader("authorization");
			if(token == null || token.equals("")) throw new RuntimeException("Missing Fields");
			
			principal = (Principal) req.getAttribute("principal");
			
			principal = userService.auth(principal);
		}
		
		if(principal != null) {
			String token = principalEncoder.encodePrincipal(principal);
			resp.addHeader("authorization", token);
		}
		
		return principal;
		
	}
	
}
