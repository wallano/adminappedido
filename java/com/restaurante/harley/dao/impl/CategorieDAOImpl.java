package com.restaurante.harley.dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.harley.dao.CategorieDAO;
import com.restaurante.harley.entity.Categorie;
import com.restaurante.harley.model.CategorieInfo;
import com.restaurante.harley.model.PaginationResult;

@Transactional
public class CategorieDAOImpl implements CategorieDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	final static String IMAGE_RESOURCE_PATH_CATEGORIES = "/src/main/webapp/resource/images/categories/";
 
    public Categorie findCategorie(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Categorie.class);
        crit.add(Restrictions.eq("code", code));
        return (Categorie) crit.uniqueResult();
    }
    public CategorieInfo findCategorieInfo(String code) {
        Categorie categorie = this.findCategorie(code);
        if (categorie == null) {
            return null;
        }
        return new CategorieInfo(categorie.getCode(), categorie.getName(), categorie.getDescription(), categorie.getPath());
    }
    
    public void deleteCategorieInfo(String code) {
        Categorie categorie = this.findCategorie(code);
        if (categorie == null) {
            return;
        }else{
        	this.sessionFactory.getCurrentSession().delete(categorie);
        }
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public void save(CategorieInfo categorieInfo) throws IOException {
        String code = categorieInfo.getCode();
 
        Categorie categorie = null;
        
        String relativePath = System.getProperty("user.dir");
        String absolutePath = relativePath + IMAGE_RESOURCE_PATH_CATEGORIES;
         
        boolean isNew = false;
        if (code != null) {
        	categorie = this.findCategorie(code);
        }
        if (categorie == null) {
            isNew = true;
            categorie = new Categorie();
            categorie.setCreate_Date(new Date());
        }
        
        categorie.setCode(code);
        categorie.setName(categorieInfo.getName());
        categorie.setDescription(categorieInfo.getDescription());
        categorie.setPath(absolutePath + categorieInfo.getFileData().getOriginalFilename());
         
        if (categorieInfo.getFileData() != null) {
            byte[] image = categorieInfo.getFileData().getBytes();
            Path path = Paths.get(absolutePath + categorieInfo.getFileData().getOriginalFilename());
          Files.write(path, image);
//       
        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(categorie);
        }
        
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public PaginationResult<CategorieInfo> queryCategories(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + CategorieInfo.class.getName() //
                + "(p.code, p.name, p.description, p.path) " + " from "//
                + Categorie.class.getName() + " p ";
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
        return new PaginationResult<CategorieInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<CategorieInfo> queryCategories(int page, int maxResult, int maxNavigationPage) {
        return queryCategories(page, maxResult, maxNavigationPage, null);
    }
	
	public List<CategorieInfo> listCategories() {
		String sql = "Select new " + CategorieInfo.class.getName() //
                + "(p.code, p.name, p.description, p.path) " + " from "//
                + Categorie.class.getName() + " p ";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<CategorieInfo> categories = query.list();
		
		return categories;
	}

}
