package com.revtaroom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revtaroom.dtos.Credentials;
import com.revtaroom.dtos.Principal;
import com.revtaroom.dtos.validators.CredentialsValidator;
import com.revtaroom.entities.User;
import com.revtaroom.entities.UserRole;
import com.revtaroom.exceptions.BadRequest;
import com.revtaroom.exceptions.ServerError;
import com.revtaroom.repositories.UserRepository;
import com.revtaroom.security.EncryptUtil;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CredentialsValidator credValidator;
	
	public List<User> getAll() throws RuntimeException {
		
		return userRepo.findAll();
		
	}
	
	public Principal auth(Principal principal) throws RuntimeException {
		
		User user = null;
		
		try {
			
			String username = principal.getUsername();
			user = userRepo.findByUsername(username);
			
		} catch(RuntimeException e) {
			throw e;
		}
		
		return new Principal(user);
	}
	
	public Principal login(Credentials cred) throws Throwable {
		User user = null;
		
		try {
			credValidator.validate(cred);
			
			user = userRepo.findByUsername(cred.getUsername());
			
			if(user == null || !EncryptUtil.checkHash(cred.getPasswd(), user.getPasswd())) {
				throw new BadRequest(400, "The username or the password is wrong");
			}
			
		} catch(BadRequest e) {
			e.printStackTrace();
			throw e;
		} catch(RuntimeException e) {
			e.printStackTrace();
			throw new ServerError(500, e.getMessage());
		}
		
		return new Principal(user);
	}
	
	@Transactional
	public Principal createUser(User user) throws RuntimeException {
		
		try {
			
			// Set Role
			user.setRole(new UserRole(1, "USER"));
			
			// Hash Passwd
			String hash = EncryptUtil.createHash(user.getPasswd());
			user.setPasswd(hash);
			
			// Persist User
			user = userRepo.save(user);
			userRepo.flush();
			
		} catch(RuntimeException e) {
			throw e;
		}
		
		return new Principal(user);
	}

}
