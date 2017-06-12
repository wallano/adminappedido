package com.restaurante.harley.rest.controller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.restaurante.harley.dao.CategorieDAO;
import com.restaurante.harley.list.CategorieList;
import com.restaurante.harley.model.CategorieInfo;

@RestController
@EnableWebMvc
public class CategoriesController {
	
	@Autowired
	CategorieDAO categorieDAO;
	
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public String categories() {//REST Endpoint.
 
		List<CategorieInfo> categoriesInfo = categorieDAO.listCategories();
		Gson gson = new Gson();
        String json = gson.toJson(categoriesInfo);
        System.out.println(json);
        
//        JAXBContext context;
//		try {
//			for(int i = 0; i < categoriesInfo.size(); i++){
//				CategorieInfo categorieInfoContext = categoriesInfo.get(i);
//				context = JAXBContext.newInstance(CategorieInfo.class);
//				Marshaller m = context.createMarshaller();
//		        StringWriter w = new StringWriter();
//
//		        m.marshal(categorieInfoContext, w);
//		        System.out.println(w);
//			}
//			
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        
        
		return json;

    }

}
