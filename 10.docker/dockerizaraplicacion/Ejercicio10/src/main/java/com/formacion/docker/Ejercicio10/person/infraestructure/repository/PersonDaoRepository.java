package com.formacion.docker.Ejercicio10.person.infraestructure.repository;


import com.formacion.docker.Ejercicio10.person.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDaoRepository extends JpaRepository<Person,Integer> {
    @Query
    List<Person> findByUsername(String username);
}
