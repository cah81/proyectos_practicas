package com.examejpa.examenjpa.cabecerafra.application;

import com.examejpa.examenjpa.cabecerafra.infraestructure.dto.FacturaOutPutDto;
import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaInputDto;

import java.util.List;
import java.util.Optional;


public interface FacturaService {


    FacturaOutPutDto addLineFra(LineaInputDto lineaInputDto);

    String deleteFactura(int id);


    //mostrar facturas
    public List<FacturaOutPutDto> listaFacturas();





}
