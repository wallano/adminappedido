package com.restaurante.harley.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.harley.dao.SupplierDAO;
import com.restaurante.harley.entity.Client;
import com.restaurante.harley.entity.Supplier;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.SupplierInfo;

@Transactional
public class SupplierDAOImpl implements SupplierDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
 
    public Supplier findSupplier(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Supplier.class);
        crit.add(Restrictions.eq("code", code));
        return (Supplier) crit.uniqueResult();
    }
    public SupplierInfo findSupplierInfo(String code) {
    	Supplier supplier = this.findSupplier(code);
        if (supplier == null) {
            return null;
        }
        return new SupplierInfo(supplier.getCode(), supplier.getName(), supplier.getAddress(), supplier.getPhone());
    }
    
    public void deleteSupplierInfo(String code) {
    	Supplier supplier = this.findSupplier(code);
        if (supplier == null) {
            return;
        }else{
        	this.sessionFactory.getCurrentSession().delete(supplier);
        }
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public void save(SupplierInfo supplierInfo) {
        String code = supplierInfo.getCode();
 
        Supplier supplier = null;
 
        boolean isNew = false;
        if (code != null) {
        	supplier = this.findSupplier(code);
        }
        if (supplier == null) {
            isNew = true;
            supplier = new Supplier();
            
        }
        supplier.setCode(code);
        supplier.setName(supplierInfo.getName());
        supplier.setAddress(supplierInfo.getAddress());
        supplier.setPhone(supplierInfo.getPhone());
         
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(supplier);
        }
        
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public PaginationResult<SupplierInfo> querySuppliers(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + SupplierInfo.class.getName() //
                + "(p.code, p.name, p.address, p.phone) " + " from "//
                + Supplier.class.getName() + " p ";
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
        return new PaginationResult<SupplierInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<SupplierInfo> querySuppliers(int page, int maxResult, int maxNavigationPage) {
        return querySuppliers(page, maxResult, maxNavigationPage, null);
    }
	
	public List<SupplierInfo> listSuppliers() {
		String sql = "Select new " + SupplierInfo.class.getName() //
                + "(p.code, p.name, p.address, p.phone) " + " from "//
                + Client.class.getName() + " p ";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<SupplierInfo> suppliers = query.list();
		
		return suppliers;
	}

}
