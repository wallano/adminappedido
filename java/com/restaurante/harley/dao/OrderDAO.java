package com.restaurante.harley.dao;

import java.util.List;

import com.restaurante.harley.model.CartInfo;
import com.restaurante.harley.model.OrderDetailInfo;
import com.restaurante.harley.model.OrderInfo;
import com.restaurante.harley.model.PaginationResult;

public interface OrderDAO {
	
	public void saveOrder(CartInfo cartInfo);
	 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public OrderInfo getOrderInfo(String orderId);
    
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

}
