package com.restaurante.harley.dao;

import com.restaurante.harley.entity.Inventory;
import com.restaurante.harley.model.InventoryInfo;
import com.restaurante.harley.model.PaginationResult;


public interface InventoryDAO {
	
public Inventory findInventory(String code);
    
    public InventoryInfo findInventoryInfo(String code);
  
    
    public PaginationResult<InventoryInfo> queryInventories(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<InventoryInfo> queryInventories(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(InventoryInfo inventoryInfo);
    
    public void deleteInventoryInfo(String code) ;

}
