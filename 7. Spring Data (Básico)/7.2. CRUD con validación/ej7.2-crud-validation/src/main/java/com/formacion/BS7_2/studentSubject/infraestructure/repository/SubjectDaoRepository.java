package com.formacion.BS7_2.studentSubject.infraestructure.repository;

import com.formacion.BS7_2.studentSubject.domain.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectDaoRepository extends JpaRepository<StudentSubject,String> {
   // List<StudentSubject> findByStudent(Student student);

}
