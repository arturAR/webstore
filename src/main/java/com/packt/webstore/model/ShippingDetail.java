package com.packt.webstore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="order_details")
public class ShippingDetail extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 6350930334140807514L;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(nullable=false)
	private Date shippingDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
	private Address shippingAddress;
	
	
	public ShippingDetail() {
		this.shippingAddress = new Address();
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
