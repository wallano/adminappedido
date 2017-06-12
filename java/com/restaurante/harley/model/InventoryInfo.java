package com.restaurante.harley.model;

import com.restaurante.harley.entity.Inventory;


public class InventoryInfo {
	
	private String code;
    private String name;
    private double price;
    private double previous_Cost;
    private double average_Cost;
    private double current_Cost;
    private int unidades_Disponibles;
 
    private boolean newInventory=false;
 
     
    public InventoryInfo() {
    }
 
    public InventoryInfo(Inventory inventory) {
        this.code = inventory.getCode();
        this.name = inventory.getName();
        this.price = inventory.getPrice();
        this.previous_Cost = inventory.getPrevious_Cost();
        this.average_Cost = inventory.getAverage_Cost();
        this.current_Cost = inventory.getCurrent_Cost();
        this.unidades_Disponibles = inventory.getUnidades_Disponibles();
    }
 
    public InventoryInfo(String code, String name, double price, double previous_Cost,
    		double average_Cost, double current_Cost, int unidades_Disponibles) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.previous_Cost = previous_Cost;
        this.average_Cost = average_Cost;
        this.current_Cost = current_Cost;
        this.unidades_Disponibles = unidades_Disponibles;
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
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getPrevious_Cost() {
		return previous_Cost;
	}

	public void setPrevious_Cost(double previous_Cost) {
		this.previous_Cost = previous_Cost;
	}

	public double getAverage_Cost() {
		return average_Cost;
	}

	public void setAverage_Cost(double average_Cost) {
		this.average_Cost = average_Cost;
	}

	public double getCurrent_Cost() {
		return current_Cost;
	}

	public void setCurrent_Cost(double current_Cost) {
		this.current_Cost = current_Cost;
	}

	public int getUnidades_Disponibles() {
		return unidades_Disponibles;
	}

	public void setUnidades_Disponibles(int unidades_Disponibles) {
		this.unidades_Disponibles = unidades_Disponibles;
	}

	public boolean isNewInventory() {
        return newInventory;
    }
 
    public void setNewInventory(boolean newInventory) {
        this.newInventory = newInventory;
    }

}
