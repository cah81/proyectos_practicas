package com.formacion.bs13_3.infraestructure.repository;

import com.formacion.bs13_3.domain.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,String> {
    Optional<File> findByName(String name);

}
