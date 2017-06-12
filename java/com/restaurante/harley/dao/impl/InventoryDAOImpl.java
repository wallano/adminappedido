package com.restaurante.harley.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.harley.dao.InventoryDAO;
import com.restaurante.harley.entity.Inventory;
import com.restaurante.harley.model.InventoryInfo;
import com.restaurante.harley.model.PaginationResult;


@Transactional
public class InventoryDAOImpl implements InventoryDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
 
    public Inventory findInventory(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Inventory.class);
        crit.add(Restrictions.eq("code", code));
        return (Inventory) crit.uniqueResult();
    }
 
    public InventoryInfo findInventoryInfo(String code) {
    	Inventory inventory = this.findInventory(code);
        if (inventory == null) {
            return null;
        }
        return new InventoryInfo(inventory.getCode(), inventory.getName(), inventory.getPrice(), inventory.getPrevious_Cost(),
        		inventory.getAverage_Cost(), inventory.getCurrent_Cost(), inventory.getUnidades_Disponibles());
    }
    
    public void deleteInventoryInfo(String code) {
    	Inventory inventory = this.findInventory(code);
        if (inventory == null) {
            return;
        }else{
        	this.sessionFactory.getCurrentSession().delete(inventory);
        }
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public void save(InventoryInfo inventoryInfo) {
        String code = inventoryInfo.getCode();
 
        Inventory inventory = null;
 
        boolean isNew = false;
        if (code != null) {
        	inventory = this.findInventory(code);
        }
        if (inventory == null) {
            isNew = true;
            inventory = new Inventory();
            inventory.setCreate_Date(new Date());
        }
        inventory.setCode(code);
        inventory.setName(inventoryInfo.getName());
        inventory.setPrice(inventoryInfo.getPrice());
        inventory.setPrevious_Cost(inventoryInfo.getPrevious_Cost());
        inventory.setAverage_Cost(inventoryInfo.getAverage_Cost());
        inventory.setCurrent_Cost(inventoryInfo.getCurrent_Cost());
        inventory.setUnidades_Disponibles(inventoryInfo.getUnidades_Disponibles());
 
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(inventory);
        }
        
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public PaginationResult<InventoryInfo> queryInventories(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + InventoryInfo.class.getName() //
                + "(p.code, p.name, p.price, p.previous_Cost, p.average_Cost, p.current_Cost, p.unidades_Disponibles) " + " from "//
                + Inventory.class.getName() + " p ";
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
        return new PaginationResult<InventoryInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<InventoryInfo> queryInventories(int page, int maxResult, int maxNavigationPage) {
        return queryInventories(page, maxResult, maxNavigationPage, null);
    }

}
