package com.examejpa.examenjpa.lineafra.domain;

import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lineaFactura")
public class LineasFra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer idFra;
    @Column(nullable = false)
    String ProNomb;

    double cantidad;
    double precio;




    public LineasFra(String proNomb, double cantidad, double precio) {
        this.ProNomb = proNomb;
        this.cantidad = cantidad;
        this.precio = precio;

    }



}
