package com.revtaroom.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.revtaroom.dtos.Principal;

@Aspect
@Component
public class AuthAspect {

	@Pointcut("@annotation(com.revtaroom.aop.Secured)")
	public void secureEndpoints() {}
	
	@Around(value = "secureEndpoints()")
	public Object secureEndpoint(ProceedingJoinPoint pjp) throws Throwable {
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		Secured controllerAnnotation = method.getAnnotation(Secured.class);
		
		boolean allowAllAuth = controllerAnnotation.allowAllAuth();
		List<String> allowedRoles = Arrays.asList(controllerAnnotation.allowedRoles());
		HttpServletRequest req = (HttpServletRequest) pjp.getArgs()[0];
		Principal principal = (Principal) req.getAttribute("principal");
		
		if(principal == null) {
			throw new SecurityException("An unauthorized request was made.");
		}
		
		if(!allowAllAuth && !allowedRoles.contains(principal.getRole().getName())) {
			throw new SecurityException("A forbidden request was made by " + principal.getUsername());
		}
		
		return pjp.proceed();
	}
	
}
