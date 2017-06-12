package com.restaurante.harley.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the forma_pago database table.
 * 
 */
@Entity
@Table(name="forma_pago")
@NamedQuery(name="FormaPago.findAll", query="SELECT f FROM FormaPago f")
public class FormaPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String estado;

	@Column(name="forma_pago")
	private String formaPago;

	@Column(name="id_producto")
	private String idProducto;

	public FormaPago() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

}