package com.examejpa.examenjpa.lineafra.application;

import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaOutPutDto;
import com.examejpa.examenjpa.lineafra.domain.LineasFra;

import java.util.List;
import java.util.Optional;

public interface LineasFacturaService {
 /*   public FacturaOutputDto CrearLineaFactura(LineaInputDto lineaInputDto,Integer id);

    public List<LineaOutputDto> mostrarLineasCabecera();

    public List<FacturaOutputDto> mostrarFacturas();*/


    public List<LineasFra> finAll();

    public Optional<LineasFra> findById(Integer id);

    public LineasFra save(LineasFra detalleVenta);

    public LineasFra update(LineasFra detalleVenta, Integer id);

    public List<LineaOutPutDto> mostrarLineasCabecera();





}
