package com.restaurante.harley.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sellers database table.
 * 
 */
@Entity
@Table(name="sellers")
@NamedQuery(name="Seller.findAll", query="SELECT p FROM Seller p")
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone")
	private String phone;

	public Seller() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	

}