package com.backend.client.infraestructure.repository;

import com.backend.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findClientByEmail(String email);
}
