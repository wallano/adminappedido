package com.restaurante.harley.dao;

import java.util.List;

import com.restaurante.harley.entity.Seller;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.SellerInfo;


public interface SellerDAO {
	
public Seller findSeller(String code);
    
    public SellerInfo findSellerInfo(String code) ;
  
    
    public PaginationResult<SellerInfo> querySellers(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<SellerInfo> querySellers(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(SellerInfo sellerInfo);
    
    public void deleteSellerInfo(String code) ;
    
    public List<SellerInfo> listSellers();

}
