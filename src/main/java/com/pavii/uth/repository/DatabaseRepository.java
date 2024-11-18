package com.pavii.uth.repository;

import com.pavii.uth.data.ClientesResponse;
import com.pavii.uth.data.ProductosResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DatabaseRepository {

	@Headers({
	    "Accept: application/vnd.github.v3.full+json",
	    "User-Agent: sistemadefacturacionfarma-salud"
	})
	@GET("/pls/apex/wilmer_murillo/appfarmasalud/clientes")
	Call<ClientesResponse> obtenerClientes();
	
	@Headers({
	    "Accept: application/vnd.github.v3.full+json",
	    "User-Agent: sistemadefacturacionfarma-salud"
	})
	@GET("/pls/apex/wilmer_murillo/appfarmasalud/productos")
	Call<ProductosResponse> obtenerProductos();
	
	
	
}
