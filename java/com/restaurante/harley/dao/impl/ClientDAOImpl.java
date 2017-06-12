package com.restaurante.harley.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.harley.dao.ClientDAO;
import com.restaurante.harley.entity.Client;
import com.restaurante.harley.model.ClientInfo;
import com.restaurante.harley.model.PaginationResult;

@Transactional
public class ClientDAOImpl implements ClientDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
 
    public Client findClient(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Client.class);
        crit.add(Restrictions.eq("code", code));
        return (Client) crit.uniqueResult();
    }
    public ClientInfo findClientInfo(String code) {
    	Client client = this.findClient(code);
        if (client == null) {
            return null;
        }
        return new ClientInfo(client.getCode(), client.getName(), client.getAddress(), client.getPhone());
    }
    
    public void deleteClientInfo(String code) {
        Client client = this.findClient(code);
        if (client == null) {
            return;
        }else{
        	this.sessionFactory.getCurrentSession().delete(client);
        }
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public void save(ClientInfo clientInfo) {
        String code = clientInfo.getCode();
 
        Client client = null;
 
        boolean isNew = false;
        if (code != null) {
        	client = this.findClient(code);
        }
        if (client == null) {
            isNew = true;
            client = new Client();
            
        }
        client.setCode(code);
        client.setName(clientInfo.getName());
        client.setAddress(clientInfo.getAddress());
        client.setPhone(clientInfo.getPhone());
         
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(client);
        }
        
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public PaginationResult<ClientInfo> queryClients(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + ClientInfo.class.getName() //
                + "(p.code, p.name, p.address, p.phone) " + " from "//
                + Client.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        //sql += " order by p.createDate desc ";
        //
        Session session = sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
        }
        return new PaginationResult<ClientInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<ClientInfo> queryClients(int page, int maxResult, int maxNavigationPage) {
        return queryClients(page, maxResult, maxNavigationPage, null);
    }
	
	public List<ClientInfo> listClients() {
		String sql = "Select new " + ClientInfo.class.getName() //
                + "(p.code, p.name, p.address, p.phone) " + " from "//
                + Client.class.getName() + " p ";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<ClientInfo> clients = query.list();
		
		return clients;
	}

}
