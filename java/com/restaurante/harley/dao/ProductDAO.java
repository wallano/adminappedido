package com.restaurante.harley.dao;

import java.io.IOException;
import java.util.List;

import com.restaurante.harley.entity.Product;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.ProductInfo;

public interface ProductDAO {
	
public Product findProduct(String code);
    
    public ProductInfo findProductInfo(String code) ;
    
    public List<ProductInfo> findProductsByCategorie(String codeCategorie) ;
  
    
    public PaginationResult<ProductInfo> queryProducts(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(ProductInfo productInfo) throws IOException;
    
    public void deleteProductInfo(String code) ;

}
