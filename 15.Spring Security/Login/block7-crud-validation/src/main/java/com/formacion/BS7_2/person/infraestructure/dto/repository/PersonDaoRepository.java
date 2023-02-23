package com.formacion.BS7_2.person.infraestructure.dto.repository;

import com.formacion.BS7_2.person.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDaoRepository extends JpaRepository<Person,Integer> {
    @Query
    Person findByUsername(String username);
}
