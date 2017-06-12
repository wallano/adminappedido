package com.restaurante.harley.dao;

import java.util.List;

import com.restaurante.harley.entity.Client;
import com.restaurante.harley.model.ClientInfo;
import com.restaurante.harley.model.PaginationResult;


public interface ClientDAO {
	
public Client findClient(String code);
    
    public ClientInfo findClientInfo(String code) ;
  
    
    public PaginationResult<ClientInfo> queryClients(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<ClientInfo> queryClients(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(ClientInfo clientInfo);
    
    public void deleteClientInfo(String code) ;
    
    public List<ClientInfo> listClients();

}
