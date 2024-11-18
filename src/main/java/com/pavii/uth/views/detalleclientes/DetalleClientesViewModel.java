package com.pavii.uth.views.detalleclientes;

import java.util.List;
import com.pavii.uth.data.Clientes;

public interface DetalleClientesViewModel {
	
	void mostrarClientesEnGrid(List<Clientes> items);
	void mostrarMensajeError(String mensaje);
	void mostrarMensajeExito(String mensaje);

}
