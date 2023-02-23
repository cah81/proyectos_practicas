package com.examejpa.examenjpa.cliente.application;

import com.examejpa.examenjpa.cliente.domain.Cliente;

public interface ClienteService {

    public Cliente obtenerClientePorId(Integer Id);

    public Cliente save(Cliente cliente);

    public String delete(Integer id);



}
