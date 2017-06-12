package com.restaurante.harley.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.restaurante.harley.entity.Product;

public class ProductInfo {
	
	private String code;
    private String name;
    private double price;
    private double cost;
    private double iva;
    private double ipoconsumo;
    private String description;
    private int unidades_Disponibles;
    private String code_Categorie;
    private String path;
 
    private boolean newProduct=false;
 
    private CommonsMultipartFile fileData;
 
    public ProductInfo() {
    }
 
    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.cost = product.getCost();
        this.iva = product.getIva();
        this.ipoconsumo = product.getIpoconsumo();
        this.description = product.getDescription();
        this.unidades_Disponibles = product.getUnidades_Disponibles();
        this.code_Categorie = product.getCode_Categorie();
        this.path = product.getPath();
    }
 
    public ProductInfo(String code, String name, double price, double cost,
    		double iva, double ipoconsumo, String description, int unidades_Disponibles,
    		String code_Categorie, String path) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.iva = iva;
        this.ipoconsumo = ipoconsumo;
        this.description = description;
        this.unidades_Disponibles = unidades_Disponibles;
        this.code_Categorie = code_Categorie;
        this.path = path;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnidades_Disponibles() {
		return unidades_Disponibles;
	}

	public void setUnidades_Disponibles(int unidades_Disponibles) {
		this.unidades_Disponibles = unidades_Disponibles;
	}
	
	public String getCode_Categorie() {
		return code_Categorie;
	}

	public void setCode_Categorie(String code_Categorie) {
		this.code_Categorie = code_Categorie;
	}

	public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return newProduct;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    

}
