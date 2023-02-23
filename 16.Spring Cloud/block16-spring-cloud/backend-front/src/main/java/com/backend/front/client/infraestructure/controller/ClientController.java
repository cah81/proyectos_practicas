package com.backend.front.client.infraestructure.controller;

import com.backend.front.client.feign.FeignBack;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {


    @Autowired
    FeignBack feignBack;

    @GetMapping("/client/{id}")
    public ClientOutputDto showById(@PathVariable("id") Long id){
        return feignBack.showById(id);
    }
    @GetMapping("/getall")
    public List<ClientOutputDto> findall(){
        return feignBack.findall();
    }

    @GetMapping("/trip/{id}")
    public TripOutputDto getTripById(@PathVariable Integer id){
        return feignBack.getTripById(id);
    }
}
