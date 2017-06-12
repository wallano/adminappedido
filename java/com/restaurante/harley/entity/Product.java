package com.restaurante.harley.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
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

	@Column(name="price")
	private double price;
	
	@Column(name="unidades_Disponibles")
	private int unidades_Disponibles;
	
	@Column(name="cost")
	private double cost;
	
	@Column(name="iva")
	private double iva;
	
	@Column(name="ipoconsumo")
	private double ipoconsumo;
	
	@Column(name="code_Categorie")
	private String code_Categorie;
	
	@Column(name="image_Name")
	private String path;
	
	//@ManyToOne
	//@JoinColumn(name="code")
	//private Categorie categorie;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="product")
	private List<OrderDetail> orderDetails;

	public Product() {
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getIpoconsumo() {
		return ipoconsumo;
	}

	public void setIpoconsumo(double ipoconsumo) {
		this.ipoconsumo = ipoconsumo;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setProduct(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setProduct(null);

		return orderDetail;
	}

	public String getCode_Categorie() {
		return code_Categorie;
	}

	public void setCode_Categorie(String code_Categorie) {
		this.code_Categorie = code_Categorie;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	

}