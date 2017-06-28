package com.packt.webstore.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.packt.webstore.model.WebstoreUser;

public interface UserService extends UserDetailsService {
	
	void save(WebstoreUser user);
	WebstoreUser findByEmail(String email);
	List<WebstoreUser> findAll();
	void delete(Long id);
	WebstoreUser findOne(Long id);

}
