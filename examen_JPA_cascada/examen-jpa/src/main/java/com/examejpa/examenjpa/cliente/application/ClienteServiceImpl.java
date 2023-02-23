package com.examejpa.examenjpa.cliente.application;

import com.examejpa.examenjpa.cliente.domain.Cliente;
import com.examejpa.examenjpa.cliente.infraestructure.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public String delete(Integer id) {
        clienteRepository.findById(id).orElseThrow(()->new RuntimeException("does not exisy"));
        clienteRepository.deleteById(id);
        return "deleted";
    }


}
