package com.restaurante.harley.model;


import com.restaurante.harley.entity.Seller;

public class SellerInfo {
	
	private String code;
    private String name;
    private String address;
    private String phone;
    
    private boolean newSeller=false;
 
    
    public SellerInfo() {
    }
 
    public SellerInfo(Seller seller) {
        this.code = seller.getCode();
        this.name = seller.getName();
        this.address = seller.getAddress();
        this.phone = seller.getPhone();
    }
 
    public SellerInfo(String code, String name, String address, String phone) {
        this.code = code;
        this.name = name;
        this.address =address;
        this.phone = phone;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
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

	public boolean isNewSeller() {
        return newSeller;
    }
 
    public void setNewClient(boolean newSeller) {
        this.newSeller = newSeller;
    }

}
