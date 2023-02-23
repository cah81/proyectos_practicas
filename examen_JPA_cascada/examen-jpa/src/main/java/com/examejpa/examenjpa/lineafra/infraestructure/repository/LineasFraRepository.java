package com.examejpa.examenjpa.lineafra.infraestructure.repository;

import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineasFraRepository extends JpaRepository<LineasFra,Integer> {
}
