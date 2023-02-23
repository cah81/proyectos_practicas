package com.formacionspring.apirest.service;

import java.util.List;

import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.entity.Region;

public interface ClienteService {
	//metodo para mostrar todos los clientes
	public List<Cliente> mostrarTodos();
	//metodo para mostrar un cliente por id
	public Cliente mostrarPorId(Long id);
	//metodo para guardar un cliente
	public Cliente guardar(Cliente cliente);
	//metodo para borrar un cliente
	public void borrar(Long id);
	
	//metodo para mostrar todas las regiones
	public List<Region> mostrarRegiones();
	
}
