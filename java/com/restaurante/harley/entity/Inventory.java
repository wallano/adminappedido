package com.restaurante.harley.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
@NamedQuery(name="Inventory.findAll", query="SELECT p FROM Inventory p")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Column(name="name")
	private String name;

	@Column(name="price")
	private double price;
	
	@Column(name="unidades_Disponibles")
	private int unidades_Disponibles;
	
	@Column(name="previous_Cost")
	private double previous_Cost;
	
	@Column(name="average_Cost")
	private double average_Cost;
	
	@Column(name="current_Cost")
	private double current_Cost;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_Date;
	
	public Inventory() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreate_Date() {
		return this.create_Date;
	}

	public void setCreate_Date(Date create_Date) {
		this.create_Date = create_Date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getUnidades_Disponibles() {
		return unidades_Disponibles;
	}

	public void setUnidades_Disponibles(int unidades_Disponibles) {
		this.unidades_Disponibles = unidades_Disponibles;
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

}