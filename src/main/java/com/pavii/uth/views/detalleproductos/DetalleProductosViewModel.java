package com.pavii.uth.views.detalleproductos;

import java.util.List;
import com.pavii.uth.data.Productos;

public interface DetalleProductosViewModel {
	void mostrarProductosEnGrid(List<Productos> items);
	void mostrarMensajeError(String mensaje);
	void mostrarMensajeExito(String mensaje);

}
