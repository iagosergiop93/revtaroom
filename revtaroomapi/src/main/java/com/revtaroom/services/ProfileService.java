package com.revtaroom.services;

import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.revtaroom.dtos.Principal;
import com.revtaroom.entities.Profile;
import com.revtaroom.repositories.ProfileRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter @Setter @NoArgsConstructor
public class ProfileService {
	
	private ProfileRepository profileRepository;
	
	@Autowired
	public ProfileService(ProfileRepository profileRepository) {
		super();
		this.profileRepository = profileRepository;
	}
	
	@Async
	@Transactional
	public CompletableFuture<Profile> insertNewProfile(Principal principal) {
		Profile profile = new Profile();
		profile.setUserId(principal.getId());
		
		profile = profileRepository.save(profile);
		
		return CompletableFuture.completedFuture(profile);
	}
	
}
