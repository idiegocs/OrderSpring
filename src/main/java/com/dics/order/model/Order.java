package com.dics.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Order {
	
	private  long orderId;
	
	private Date fechaOrder;
	
	private String rqUID;
	
	private double total;
	
	private List<Producto> productos=new ArrayList<Producto>();
	
	
	

	public Order() {
		super();
		
	}

	public Order(long orderId, Date fechaOrder, double total, List<Producto> productos) {
		super();
		this.orderId = orderId;
		this.fechaOrder = fechaOrder;
		this.total = total;
		this.productos = productos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [orderId=");
		builder.append(orderId);
		builder.append(", fechaOrder=");
		builder.append(fechaOrder);
		builder.append(", total=");
		builder.append(total);
		builder.append(", productos=");
		builder.append(productos);
		builder.append("]");
		return builder.toString();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getFechaOrder() {
		return fechaOrder;
	}

	public void setFechaOrder(Date fechaOrder) {
		this.fechaOrder = fechaOrder;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getRqUID() {
		return rqUID;
	}

	public void setRqUID(String rqUID) {
		this.rqUID = rqUID;
	}
	
	
	
	
	

}
