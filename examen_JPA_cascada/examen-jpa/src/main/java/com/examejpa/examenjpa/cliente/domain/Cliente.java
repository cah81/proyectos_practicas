package com.examejpa.examenjpa.cliente.domain;

import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import com.examejpa.examenjpa.cliente.infraestructure.dto.ClienteOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer idCli;
    @Column(nullable = false,length = 100)
    String name;




}
