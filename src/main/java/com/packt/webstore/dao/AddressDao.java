package com.packt.webstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packt.webstore.model.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

}
