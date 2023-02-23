package com.examejpa.examenjpa.cliente.infraestructure.repository;

import com.examejpa.examenjpa.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
