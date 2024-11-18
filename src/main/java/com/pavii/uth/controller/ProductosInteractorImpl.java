package com.pavii.uth.controller;

import com.pavii.uth.data.ProductosResponse;
import com.pavii.uth.views.detalleproductos.DetalleProductosViewModel;
import com.pavii.uth.repository.DatabaseRepositoryImpl;


public class ProductosInteractorImpl implements ProductosInteractor{
	
	private DatabaseRepositoryImpl modelo;
	private DetalleProductosViewModel vista;

	public ProductosInteractorImpl(DetalleProductosViewModel vista) {
		super();
		this.vista = vista;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 3000L);
	}
	
	@Override
	public void consultarProductos() {
		try {
			ProductosResponse respuesta = this.modelo.consultarProductos();
			if(respuesta == null || respuesta.getCount() == 0 || respuesta.getItems() == null) {
				this.vista.mostrarMensajeError("No hay Productos disponibles");
			}else {
				this.vista.mostrarProductosEnGrid(respuesta.getItems());
			}
		}catch(Exception error) {
			error.printStackTrace();
		}
	}



}