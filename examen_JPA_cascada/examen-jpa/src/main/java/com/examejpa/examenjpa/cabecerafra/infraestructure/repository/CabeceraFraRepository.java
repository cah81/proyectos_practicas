package com.examejpa.examenjpa.cabecerafra.infraestructure.repository;

import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabeceraFraRepository extends JpaRepository<CabeceraFra,Integer> {

}
