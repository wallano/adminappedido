package com.restaurante.harley.rest.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.restaurante.harley.dao.CategorieDAO;
import com.restaurante.harley.dao.ProductDAO;
import com.restaurante.harley.list.CategorieList;
import com.restaurante.harley.model.CategorieInfo;
import com.restaurante.harley.model.ProductInfo;

@RestController
@EnableWebMvc
public class ProductsController {
	
	@Autowired
	ProductDAO productDAO;
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/products/{codeCategorie}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductInfo> products(@PathVariable String codeCategorie) {//REST Endpoint.
 
		List<ProductInfo> productsInfo = productDAO.findProductsByCategorie(codeCategorie);
		Gson gson = new Gson();
        String json = gson.toJson(productsInfo);
        System.out.println(json);
		return productsInfo;
		
        
    }

}
