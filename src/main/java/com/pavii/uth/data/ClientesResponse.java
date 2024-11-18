package com.pavii.uth.data;

import java.util.List;

public class ClientesResponse {
	private List<Clientes> items; 
	private int count;
	
	public List<Clientes> getItems() {
		return items;
	}
	public void setItems(List<Clientes> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
