package com.formacion.BS7_2.student.infraestructure.repository;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentDaoRepository extends JpaRepository<Student,String> {
    Optional<Student> findByPerson(Person person);

}
