package com.restaurante.harley.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.harley.dao.SellerDAO;
import com.restaurante.harley.dao.SupplierDAO;
import com.restaurante.harley.entity.Client;
import com.restaurante.harley.entity.Seller;
import com.restaurante.harley.entity.Supplier;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.SellerInfo;
import com.restaurante.harley.model.SupplierInfo;

@Transactional
public class SellerDAOImpl implements SellerDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
 
    public Seller findSeller(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Seller.class);
        crit.add(Restrictions.eq("code", code));
        return (Seller) crit.uniqueResult();
    }
    public SellerInfo findSellerInfo(String code) {
    	Seller seller = this.findSeller(code);
        if (seller == null) {
            return null;
        }
        return new SellerInfo(seller.getCode(), seller.getName(), seller.getAddress(), seller.getPhone());
    }
    
    public void deleteSellerInfo(String code) {
    	Seller seller = this.findSeller(code);
        if (seller == null) {
            return;
        }else{
        	this.sessionFactory.getCurrentSession().delete(seller);
        }
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public void save(SellerInfo sellerInfo) {
        String code = sellerInfo.getCode();
 
        Seller seller = null;
 
        boolean isNew = false;
        if (code != null) {
        	seller = this.findSeller(code);
        }
        if (seller == null) {
            isNew = true;
            seller = new Seller();
            
        }
        seller.setCode(code);
        seller.setName(sellerInfo.getName());
        seller.setAddress(sellerInfo.getAddress());
        seller.setPhone(sellerInfo.getPhone());
         
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(seller);
        }
        
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public PaginationResult<SellerInfo> querySellers(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + SellerInfo.class.getName() //
                + "(p.code, p.name, p.address, p.phone) " + " from "//
                + Seller.class.getName() + " p ";
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
        return new PaginationResult<SellerInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<SellerInfo> querySellers(int page, int maxResult, int maxNavigationPage) {
        return querySellers(page, maxResult, maxNavigationPage, null);
    }
	
	public List<SellerInfo> listSellers() {
		String sql = "Select new " + SellerInfo.class.getName() //
                + "(p.code, p.name, p.address, p.phone) " + " from "//
                + Client.class.getName() + " p ";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<SellerInfo> sellers = query.list();
		
		return sellers;
	}

}
