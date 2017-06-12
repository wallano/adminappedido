package com.restaurante.harley.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="amount")
	private double amount;

	@Column(name="customer_Address")
	private String customer_Address;

	@Column(name="customer_Email")
	private String customer_Email;

	@Column(name="customer_Name")
	private String customer_Name;

	@Column(name="customer_Phone")
	private String customer_Phone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date order_Date;

	@Column(name="order_Num")
	private int order_Num;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetails;

	public Order() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomer_Address() {
		return this.customer_Address;
	}

	public void setCustomer_Address(String customer_Address) {
		this.customer_Address = customer_Address;
	}

	public String getCustomer_Email() {
		return this.customer_Email;
	}

	public void setCustomer_Email(String customer_Email) {
		this.customer_Email = customer_Email;
	}

	public String getCustomer_Name() {
		return this.customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public String getCustomer_Phone() {
		return this.customer_Phone;
	}

	public void setCustomer_Phone(String customer_Phone) {
		this.customer_Phone = customer_Phone;
	}

	public Date getOrder_Date() {
		return this.order_Date;
	}

	public void setOrder_Date(Date order_Date) {
		this.order_Date = order_Date;
	}

	public int getOrder_Num() {
		return this.order_Num;
	}

	public void setOrder_Num(int order_Num) {
		this.order_Num = order_Num;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);

		return orderDetail;
	}

}