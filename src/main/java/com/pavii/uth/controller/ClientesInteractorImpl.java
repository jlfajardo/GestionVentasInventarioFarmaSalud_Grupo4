package com.pavii.uth.controller;

import com.pavii.uth.data.ClientesResponse;
import com.pavii.uth.views.detalleclientes.DetalleClientesViewModel;
import com.pavii.uth.repository.DatabaseRepositoryImpl;


public class ClientesInteractorImpl implements ClientesInteractor{
	
	private DatabaseRepositoryImpl modelo;
	private DetalleClientesViewModel vista;

	public ClientesInteractorImpl(DetalleClientesViewModel vista) {
		super();
		this.vista = vista;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 3000L);
	}
	
	@Override
	public void consultarClientes() {
		try {
			ClientesResponse respuesta = this.modelo.consultarClientes();
			if(respuesta == null || respuesta.getCount() == 0 || respuesta.getItems() == null) {
				this.vista.mostrarMensajeError("No hay Clientes disponibles");
			}else {
				this.vista.mostrarClientesEnGrid(respuesta.getItems());
			}
		}catch(Exception error) {
			error.printStackTrace();
		}
	}



}
