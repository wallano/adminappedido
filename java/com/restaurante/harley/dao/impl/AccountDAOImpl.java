package com.restaurante.harley.dao.impl;

import com.restaurante.harley.dao.AccountDAO;
import com.restaurante.harley.entity.Account;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountDAOImpl implements AccountDAO{
	
	@Autowired
    private SessionFactory sessionFactory;

	public Account findAccount(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Criterion criterion = Restrictions.eq("userName", userName);
		Criteria crit = session.createCriteria(Account.class);
        crit.add(criterion);
        return (Account) crit.uniqueResult();
	}
 
    
}
