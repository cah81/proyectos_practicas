package com.examejpa.examenjpa.lineafra.infraestructure.dto;

import com.examejpa.examenjpa.cliente.infraestructure.dto.ClienteOutputDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class LineasFacturaDto implements Serializable {
    private  Integer id;
    private  double importe;
    private ClienteOutputDto clienteOutputDto;
    List<LineaOutPutDto> lineaOutPutDtoList;
}