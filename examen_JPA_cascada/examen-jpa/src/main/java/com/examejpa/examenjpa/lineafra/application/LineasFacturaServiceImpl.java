package com.examejpa.examenjpa.lineafra.application;

import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaOutPutDto;
import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import com.examejpa.examenjpa.lineafra.infraestructure.repository.LineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LineasFacturaServiceImpl implements LineasFacturaService {

    @Autowired
    LineasFraRepository lineasFraRepository;


    @Override
    public List<LineasFra> finAll() {
        return lineasFraRepository.findAll();
    }

    @Override
    public Optional<LineasFra> findById(Integer id) {
        return lineasFraRepository.findById(id);
    }

    @Override
    public LineasFra save(LineasFra detalleVenta) {
        return lineasFraRepository.save(detalleVenta);
    }

    @Override
    public LineasFra update(LineasFra detalleVenta, Integer id) {
        return null;
    }

    @Override
    public List<LineaOutPutDto> mostrarLineasCabecera() {
        List<LineaOutPutDto>lineasList = new ArrayList<>();
        lineasFraRepository.findAll().forEach(lineas->{
            LineaOutPutDto lineaOutPutDto = new LineaOutPutDto(lineas);
            lineasList.add(lineaOutPutDto);
        });
        return lineasList;
    }
  /*  @Autowired
    CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    CabeceraService cabeceraService;

    @Autowired
    LineasFraRepository lineasFraRepository;



    @Autowired
    ClienteService clienteService;


    @Override
    public FacturaOutputDto CrearLineaFactura(LineaInputDto lineaInputDto,Integer id) {

        CabeceraFra cabeceraFra= cabeceraFraRepository.findById(id).orElseThrow(()->new RuntimeException("error"));
        LineasFactura lineasfra = new LineasFactura(lineaInputDto,cabeceraFra);
        LineasFactura newLineasFactura = lineasFraRepository.save(lineasfra);
        return new FacturaOutputDto(cabeceraFra);










    }

    @Override
    public List<LineaOutputDto> mostrarLineasCabecera() {
        List<LineaOutputDto> lineasList = new ArrayList<>();
        lineasFraRepository.findAll().forEach(lineas->{
            LineaOutputDto lineaOutputDto = new LineaOutputDto(lineas);
            lineasList.add(lineaOutputDto);
        });

        return lineasList;
    }

    @Override
    public List<FacturaOutputDto> mostrarFacturas() {
        List<FacturaOutputDto> facturasList = new ArrayList<>();
        cabeceraFraRepository.findAll().forEach(cabeceraFra -> {
            FacturaOutputDto facturaOutputDto = new FacturaOutputDto(cabeceraFra);
            facturasList.add(facturaOutputDto);
        });
        return facturasList;
    }
*/

}





