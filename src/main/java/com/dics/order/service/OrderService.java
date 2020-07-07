package com.dics.order.service;



import javax.jms.JMSException;



import com.dics.order.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface OrderService {
	
	
	public Order ingresarOrder( Order order)throws JMSException, JsonProcessingException;

}
