package com.restaurante.harley.list;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.restaurante.harley.model.CategorieInfo;

@XmlRootElement(name = "categories")
public class CategorieList {
	
	@XmlElement(required = true)
    public List<CategorieInfo> categories;

    public List<CategorieInfo> getData() {
        return categories;
    }

    public void setData(List<CategorieInfo> categories) {
        this.categories = categories;
    }

}
