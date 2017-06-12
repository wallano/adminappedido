package com.restaurante.harley.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Categorie.findAll", query="SELECT p FROM Categorie p")
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	private Date create_Date;

	@Column(name="description")
	private String description;

	@Lob
	private byte[] image;

	@Column(name="name")
	private String name;
	
	@Column(name="image_Name")
	private String path;

	public Categorie() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	

}