package com.dics.order.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dics.order.model.Order;
import com.dics.order.model.Producto;
import com.dics.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;


@RestController
public class OrderController {
	
	@Autowired
	OrderService ordenServ;
	
	@ApiOperation(value="Get all Orders",
            notes="Detail all Orders")
	@GetMapping(value = "/v1/order")
	public List<Order> getOrdes()
	{
		List<Order> ordenes= new ArrayList<Order>();
		Order order=new Order();
		order.setFechaOrder(new Date());
		order.setOrderId(1111l);
		order.setTotal(5000);
		List<Producto>  prods=order.getProductos();
		
		Producto pro= new Producto("zelda", "Juegos", 240000D, 1L);
		prods.add(pro);
		
		ordenes.add(order);
		
		return ordenes;
	}
	
	@ApiOperation(value="Post Order",
            notes="Insert Order Products")
	@PostMapping(value = "/v1/order")
	public Order setOrder(@RequestBody Order order) throws JMSException, JsonProcessingException
	{
		Order ordenDB=ordenServ.ingresarOrder(order);
		
		return ordenDB;
	}

}
