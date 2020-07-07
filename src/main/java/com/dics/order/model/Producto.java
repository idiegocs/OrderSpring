package com.dics.order.model;

public class Producto {

	
	private String nombre;
	private String categoria;
	private Double precio;
	private long unidades;
	
	
	
	public Producto(String nombre, String categoria, Double precio, long unidades) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.unidades = unidades;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [nombre=");
		builder.append(nombre);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", unidades=");
		builder.append(unidades);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public long getUnidades() {
		return unidades;
	}
	public void setUnidades(long unidades) {
		this.unidades = unidades;
	}
	
	
	
}
