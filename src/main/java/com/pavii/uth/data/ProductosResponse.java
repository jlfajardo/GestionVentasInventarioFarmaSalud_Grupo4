package com.pavii.uth.data;

import java.util.List;

public class ProductosResponse {
	private List<Productos> items; 
	private int count;
	
	public List<Productos> getItems() {
		return items;
	}
	public void setItems(List<Productos> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}