package com.examejpa.examenjpa.cabecerafra.domain;

import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import com.examejpa.examenjpa.cliente.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cabeceraFra")
public class CabeceraFra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    //int cli_codi;
    double importeFra=0;

    @ManyToOne
    @JoinColumn(name = "idCli")
    Cliente cliente;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinColumn(name = "LINEAS_FRA")
    List<LineasFra> items = new ArrayList<>();

    public CabeceraFra(double importeFra,Cliente cliente) {

        this.importeFra = importeFra;
        setCliente(cliente);
    }

    public CabeceraFra(double importeFra, Cliente cliente, List<LineasFra> items) {
        this.importeFra = importeFra;
        this.cliente = cliente;
        this.items = items;
    }

    public CabeceraFra(Cliente cliente) {

        this.importeFra = importeFra;
        setCliente(cliente);
    }
}










