package com.restaurante.harley.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.restaurante.harley.entity.Categorie;

public class CategorieInfo {
	
	private String code;
    private String name;
    private String description;
    private String path;
    
    private boolean newCategorie=false;
 
    private CommonsMultipartFile fileData;
 
    public CategorieInfo() {
    }
 
    public CategorieInfo(Categorie categorie) {
        this.code = categorie.getCode();
        this.name = categorie.getName();
        this.description = categorie.getDescription();
        this.path = categorie.getPath();
    }
 
    public CategorieInfo(String code, String name, String description, String path) {
        this.code = code;
        this.name = name;
        this.description = description;
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
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CommonsMultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewCategorie() {
        return newCategorie;
    }
 
    public void setNewCategorie(boolean newCategorie) {
        this.newCategorie = newCategorie;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    

}
