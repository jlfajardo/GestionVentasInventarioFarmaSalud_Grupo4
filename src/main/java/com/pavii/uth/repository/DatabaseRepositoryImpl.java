package com.pavii.uth.repository;

import java.io.IOException;

import com.pavii.uth.data.ClientesResponse;
import com.pavii.uth.data.ProductosResponse;

import retrofit2.Response;
import retrofit2.Call;
import okhttp3.ResponseBody;

public class DatabaseRepositoryImpl {

	private static DatabaseRepositoryImpl INSTANCE;
	private DatabaseClient client;
	
	//PATRON SINGLETON
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client = new DatabaseClient(url, timeout);
	}
	
	//PATRON SINGLETON
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE == null) {
			synchronized (DatabaseRepositoryImpl.class) {
				if(INSTANCE == null) {
					INSTANCE = new DatabaseRepositoryImpl(url, timeout);
				}
			}
		}
		return INSTANCE;
	}
	
	public ClientesResponse consultarClientes() throws IOException {
		Call<ClientesResponse> call = client.getDatabase().obtenerClientes();
		Response<ClientesResponse> response = call.execute();//AQUI SE PRODUCE LA LLAMADA
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public ProductosResponse consultarProductos() throws IOException {
		Call<ProductosResponse> call = client.getDatabase().obtenerProductos();
		Response<ProductosResponse> response = call.execute();//AQUI SE PRODUCE LA LLAMADA
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	
}
