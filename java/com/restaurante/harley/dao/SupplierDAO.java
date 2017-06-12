package com.restaurante.harley.dao;

import java.util.List;

import com.restaurante.harley.entity.Supplier;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.SupplierInfo;


public interface SupplierDAO {
	
public Supplier findSupplier(String code);
    
    public SupplierInfo findSupplierInfo(String code) ;
  
    
    public PaginationResult<SupplierInfo> querySuppliers(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<SupplierInfo> querySuppliers(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(SupplierInfo supplierInfo);
    
    public void deleteSupplierInfo(String code) ;
    
    public List<SupplierInfo> listSuppliers();

}
