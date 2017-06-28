package com.packt.webstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.packt.webstore.dao.UserDao;
import com.packt.webstore.model.WebstoreUser;
import com.packt.webstore.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		WebstoreUser user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("%s does not exist in the database!", email));
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		return new User(user.getEmail(), user.getPassword(), authorities);
	}
	

	@Override
	public void save(WebstoreUser user) {
		userDao.save(user);
	}

	@Override
	public WebstoreUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<WebstoreUser> findAll() {
		return userDao.findAll();
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public WebstoreUser findOne(Long id) {
		return userDao.findOne(id);
	}
	
	
}
