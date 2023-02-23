package com.examejpa.examenjpa.lineafra.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDto implements Serializable {

    private  String Producto;
    private  double cantidad;
    private  double importe;
    private  int idFra;

}