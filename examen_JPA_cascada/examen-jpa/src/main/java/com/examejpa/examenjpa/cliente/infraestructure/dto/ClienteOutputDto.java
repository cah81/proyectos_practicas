package com.examejpa.examenjpa.cliente.infraestructure.dto;

import com.examejpa.examenjpa.cliente.domain.Cliente;
import lombok.Data;

import java.io.Serializable;


@Data
public class ClienteOutputDto implements Serializable {
    private  Integer id;
    private  String name;

    public ClienteOutputDto(Cliente cliente){
        this.id = cliente.getIdCli();
        this.name = cliente.getName();
    }




}