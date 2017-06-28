package com.packt.webstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packt.webstore.model.WebstoreUser;

public interface UserDao extends JpaRepository<WebstoreUser, Long> {

	WebstoreUser findByEmail(String email);

}
