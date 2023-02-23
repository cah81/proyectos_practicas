package com.backend.front.client.application;

import com.backend.front.client.feign.FeignBack;

import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService  {
    @Autowired
    FeignBack feignBack;


    public ClientOutputDto showById(Long id) {
        //return new ClientOutputDto(clientFeign.showById(id));
        return feignBack.showById(id);

    }

    public List<ClientOutputDto> getClient(){
        return feignBack.findall();
    }
}
