package com.examejpa.examenjpa.cabecerafra.infraestructure.dto;

import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import com.examejpa.examenjpa.cliente.infraestructure.dto.ClienteOutputDto;
import com.examejpa.examenjpa.lineafra.domain.LineasFra;
import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaOutPutDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FacturaOutPutDto {
    int id;
    double importeFra;
    ClienteOutputDto clienteOutputDto;
    List<LineaOutPutDto> lineaOutPutDtoList = new ArrayList<>();

    public FacturaOutPutDto(CabeceraFra cabeceraFra){
       this.id =cabeceraFra.getId();
       this.importeFra= cabeceraFra.getImporteFra();
       List<LineasFra> lista = cabeceraFra.getItems();
       if(lista==null)
           return;
         lineaOutPutDtoList= cabeceraFra.getItems().stream()
                 .map(LineaOutPutDto::new)
                 .collect(Collectors.toList());
         clienteOutputDto = new ClienteOutputDto(cabeceraFra.getCliente());
    }
    }


