package com.example.metier;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.controllers.dto.UserRegistrationDto;
import com.example.entities.User;

public interface UserService extends UserDetailsService  {
	User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
