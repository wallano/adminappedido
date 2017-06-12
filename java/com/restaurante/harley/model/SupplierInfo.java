package com.restaurante.harley.model;


import com.restaurante.harley.entity.Supplier;

public class SupplierInfo {
	
	private String code;
    private String name;
    private String address;
    private String phone;
    
    private boolean newSupplier=false;
 
    
    public SupplierInfo() {
    }
 
    public SupplierInfo(Supplier supplier) {
        this.code = supplier.getCode();
        this.name = supplier.getName();
        this.address = supplier.getAddress();
        this.phone = supplier.getPhone();
    }
 
    public SupplierInfo(String code, String name, String address, String phone) {
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

	public boolean isNewSupplier() {
        return newSupplier;
    }
 
    public void setNewSupplier(boolean newSupplier) {
        this.newSupplier = newSupplier;
    }

}
