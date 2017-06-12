package com.restaurante.harley.dao.impl;

import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.restaurante.harley.dao.OrderDAO;
import com.restaurante.harley.dao.ProductDAO;
import com.restaurante.harley.entity.Order;
import com.restaurante.harley.entity.OrderDetail;
import com.restaurante.harley.entity.Product;
import com.restaurante.harley.model.CartInfo;
import com.restaurante.harley.model.CartLineInfo;
import com.restaurante.harley.model.CustomerInfo;
import com.restaurante.harley.model.OrderDetailInfo;
import com.restaurante.harley.model.OrderInfo;
import com.restaurante.harley.model.PaginationResult;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@Transactional
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private ProductDAO productDAO;
 
    private int getMaxOrderNum() {
        String sql = "Select max(o.order_Num) from " + Order.class.getName() + " o ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }

	public void saveOrder(CartInfo cartInfo) {
		Session session = sessionFactory.getCurrentSession();
		 
        int orderNum = this.getMaxOrderNum() + 1;
        Order order = new Order();
 
        order.setId(UUID.randomUUID().toString());
        order.setOrder_Num(orderNum);
        order.setOrder_Date(new Date());
        order.setAmount(cartInfo.getAmountTotal());
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomer_Name(customerInfo.getName());
        order.setCustomer_Email(customerInfo.getEmail());
        order.setCustomer_Phone(customerInfo.getPhone());
        order.setCustomer_Address(customerInfo.getAddress());
 
        session.persist(order);
 
        List<CartLineInfo> lines = cartInfo.getCartLines();
 
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());
 
            String code = line.getProductInfo().getCode();
            Product product = this.productDAO.findProduct(code);
            detail.setProduct(product);
 
            session.persist(detail);
        }
 
        cartInfo.setOrderNum(orderNum);
		
	}

	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.order_Date, ord.order_Num, ord.amount, "
                + " ord.customer_Name, ord.customer_Address, ord.customer_Email, ord.customer_Phone) " + " from "
                + Order.class.getName() + " ord "//
                + " order by ord.order_Num desc";
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
 
        return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
	}
	
	public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }

	public OrderInfo getOrderInfo(String orderId) {
		Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrder_Date(), //
                order.getOrder_Num(), order.getAmount(), order.getCustomer_Name(), //
                order.getCustomer_Address(), order.getCustomer_Email(), order.getCustomer_Phone());
	}

	public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
		String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.product.code, d.product.name , d.quanity,d.price,d.amount) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";
 
        Session session = this.sessionFactory.getCurrentSession();
 
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
 
        return query.list();
	}
 
    
}
