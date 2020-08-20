package com.revtaroom.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Principal;
import com.revtaroom.security.PrincipalEncoder;
import com.revtaroom.security.jwt.JwtConfig;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@WebFilter
public class AuthFilter extends HttpFilter {

	private static final long serialVersionUID = 6346475414095485659L;
	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		extractPrincipal(req);
		
		chain.doFilter(req, resp);
		return;
	}
	
	/**
	 * Obtains the token from the Authorization header of the request object,
	 * validates and parses it to extract the payload (a Principal object) - 
	 * which is attached to the request object as an attribute called "principal".
	 * 
	 * If no token is present, no action is taken. The request object is not manipulated
	 * in any way. 
	 * 
	 * @param req
	 * @throws IOException
	 * @throws ServletException
	 */
	public void extractPrincipal(HttpServletRequest req) throws IOException, ServletException {
		System.out.println("Attempting to extract principal object from JWT...");
		String header = req.getHeader(JwtConfig.HEADER);
		
		if(header == null || !header.startsWith(JwtConfig.PREFIX)) {
			System.out.println("No token found with required prefix");
			return;
		}
		
		try {
			
			Principal principal = PrincipalEncoder.decodePrincipal(header);
			req.setAttribute("principal", principal);
			
		} catch (ExpiredJwtException eje) {
			System.out.println("Invalid or Expired JWT");
		} catch (Exception e) {
			System.out.println("Error parsing JWT");
			e.printStackTrace();
		}
	}

}
