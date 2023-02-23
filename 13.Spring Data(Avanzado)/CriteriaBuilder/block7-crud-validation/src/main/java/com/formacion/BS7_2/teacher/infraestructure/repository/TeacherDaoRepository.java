package com.formacion.BS7_2.teacher.infraestructure.repository;

import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.teacher.domain.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherDaoRepository extends JpaRepository<Teacher,String> {
    Optional<Teacher> findByPerson(Person person);
}
