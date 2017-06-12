package com.restaurante.harley.model;


import com.restaurante.harley.entity.Client;

public class ClientInfo {
	
	private String code;
    private String name;
    private String address;
    private String phone;
    
    private boolean newClient=false;
 
    
    public ClientInfo() {
    }
 
    public ClientInfo(Client client) {
        this.code = client.getCode();
        this.name = client.getName();
        this.address = client.getAddress();
        this.phone = client.getPhone();
    }
 
    public ClientInfo(String code, String name, String address, String phone) {
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

	public boolean isNewClient() {
        return newClient;
    }
 
    public void setNewClient(boolean newClient) {
        this.newClient = newClient;
    }

}
