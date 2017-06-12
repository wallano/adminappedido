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

import com.restaurante.harley.dao.ProductDAO;
import com.restaurante.harley.entity.Categorie;
import com.restaurante.harley.entity.Product;
import com.restaurante.harley.model.PaginationResult;
import com.restaurante.harley.model.ProductInfo;

@Transactional
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	final static String IMAGE_RESOURCE_PATH_PRODUCTS = "/src/main/webapp/resource/images/products/";
 
    public Product findProduct(String code) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("code", code));
        return (Product) crit.uniqueResult();
    }
 
    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return new ProductInfo(product.getCode(), product.getName(), product.getPrice(), product.getCost(),
        		product.getIva(), product.getIpoconsumo(), product.getDescription(), product.getUnidades_Disponibles(),
        		product.getCode_Categorie(), product.getPath());
    }
    
    public void deleteProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return;
        }else{
        	this.sessionFactory.getCurrentSession().delete(product);
        }
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public void save(ProductInfo productInfo) throws IOException {
        String code = productInfo.getCode();
 
        Product product = null;
        String relativePath = System.getProperty("user.dir");
        String absolutePath = relativePath + IMAGE_RESOURCE_PATH_PRODUCTS;
 
        boolean isNew = false;
        if (code != null) {
            product = this.findProduct(code);
        }
        if (product == null) {
            isNew = true;
            product = new Product();
            product.setCreate_Date(new Date());
        }
        product.setCode(code);
        product.setName(productInfo.getName());
        product.setPrice(productInfo.getPrice());
        product.setCost(productInfo.getCost());
        product.setIva(productInfo.getIva());
        product.setIpoconsumo(productInfo.getIpoconsumo());
        product.setDescription(productInfo.getDescription());
        product.setUnidades_Disponibles(productInfo.getUnidades_Disponibles());
        product.setCode_Categorie(productInfo.getCode_Categorie());
        product.setPath(absolutePath + productInfo.getFileData().getOriginalFilename());
 
        if (productInfo.getFileData() != null) {
            byte[] image = productInfo.getFileData().getBytes();
            if (image != null && image.length > 0) {
            	Path path = Paths.get(absolutePath + productInfo.getFileData().getOriginalFilename());
                Files.write(path, image);
 //               product.setImage(image);
            }
        }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(product);
        }
        
        this.sessionFactory.getCurrentSession().flush();
    }
 
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + ProductInfo.class.getName() //
                + "(p.code, p.name, p.price, p.cost, p.iva, p.ipoconsumo, p.description, p.unidades_Disponibles, p.code_Categorie, p.path) " + " from "//
                + Product.class.getName() + " p ";
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
        return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
    }
 
    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
        return queryProducts(page, maxResult, maxNavigationPage, null);
    }

	//@Override
	public List<ProductInfo> findProductsByCategorie(String codeCategorie) {
		 String sql = "Select new " + ProductInfo.class.getName() //
	                + "(p.code, p.name, p.price, p.cost, p.iva, p.ipoconsumo, p.description, p.unidades_Disponibles, p.code_Categorie, p.path) " + " from "//
	                + Product.class.getName() + " p ";
		 if (codeCategorie != null && codeCategorie.length() > 0) {
	            sql += " Where p.code_Categorie= :codeCategorie ";
	     }
		 Session session = sessionFactory.getCurrentSession();
		 
	     Query query = session.createQuery(sql);
	     if (codeCategorie != null && codeCategorie.length() > 0) {
	    	 query.setParameter("codeCategorie", codeCategorie);
	     }
	     System.out.println("Sentencia: "+query.toString());
	     List<ProductInfo> productsByCategorie = query.list();
		return productsByCategorie;
	}
    
}
