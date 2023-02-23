package com.examejpa.examenjpa.lineafra.infraestructure.dto;

import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaOutPutDto implements Serializable {
    private  Integer id;
    private  String producto;
    private  double cantidad;
    private  double importe;


    public LineaOutPutDto(LineasFra lineasFra){
        this.id = lineasFra.getIdFra();
        this.producto = lineasFra.getProNomb();
        this.cantidad = lineasFra.getCantidad();
        this.importe = lineasFra.getPrecio();
    }


}