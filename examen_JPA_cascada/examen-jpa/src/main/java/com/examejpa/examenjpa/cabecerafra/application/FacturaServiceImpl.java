package com.examejpa.examenjpa.cabecerafra.application;


import com.examejpa.examenjpa.cabecerafra.infraestructure.dto.FacturaOutPutDto;
import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import com.examejpa.examenjpa.cabecerafra.infraestructure.repository.CabeceraFraRepository;
import com.examejpa.examenjpa.exception.EntityNotFoundException;
import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaInputDto;

import com.examejpa.examenjpa.lineafra.infraestructure.repository.LineasFraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {


    @Autowired
    CabeceraFraRepository cabeceraFraRepository;



    //adiciona una linea a una factura existente
    @Override
    public FacturaOutPutDto addLineFra(LineaInputDto lineaInputDto) {

        Optional<CabeceraFra> factura = cabeceraFraRepository.findById(lineaInputDto.getIdFra());
        if(factura.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<LineasFra> listaLineas = factura.get().getItems();
        LineasFra linea = new LineasFra(lineaInputDto.getProducto()
                ,lineaInputDto.getCantidad(),
                 lineaInputDto.getImporte());
        listaLineas.add(linea);
        factura.get().setItems(listaLineas);
        return new FacturaOutPutDto(cabeceraFraRepository.save(factura.get()));
    }

    @Override
    public String deleteFactura(int id) {
        Optional<CabeceraFra> factura = cabeceraFraRepository.findById(id);
        if(factura.isEmpty()){
            throw new EntityNotFoundException();
        }
        cabeceraFraRepository.delete(factura.get());

        return "Factura Borrada";
    }


    //mostrar lista de facturas
    @Override
    public List<FacturaOutPutDto> listaFacturas() {

        List<FacturaOutPutDto> listaFacturas = new ArrayList<>();
        return convertirALineaOutPutDto(cabeceraFraRepository.findAll()) ;

    }
    public static List<FacturaOutPutDto> convertirALineaOutPutDto(List<CabeceraFra> lineas){
        List<FacturaOutPutDto> lineaOutPutDto = new ArrayList<>();
        for(CabeceraFra linea : lineas){
            lineaOutPutDto.add(new FacturaOutPutDto(linea));
        }
        return lineaOutPutDto;
    }

}
