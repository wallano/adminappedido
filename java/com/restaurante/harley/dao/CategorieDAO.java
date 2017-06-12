package com.restaurante.harley.dao;

import java.io.IOException;
import java.util.List;

import com.restaurante.harley.entity.Categorie;
import com.restaurante.harley.model.CategorieInfo;
import com.restaurante.harley.model.PaginationResult;


public interface CategorieDAO {
	
public Categorie findCategorie(String code);
    
    public CategorieInfo findCategorieInfo(String code) ;
  
    
    public PaginationResult<CategorieInfo> queryCategories(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<CategorieInfo> queryCategories(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(CategorieInfo categorieInfo) throws IOException;
    
    public void deleteCategorieInfo(String code) ;
    
    public List<CategorieInfo> listCategories();

}
