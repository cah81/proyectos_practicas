package com.backend.front.client.feign;





import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="backend")//,url = "http://backend-back:8080/"
public interface FeignBack {
    @GetMapping(value = "client/{id}")
    public ClientOutputDto showById(@PathVariable("id") Long idClient);

    @GetMapping("client/getall")
    public List<ClientOutputDto> findall();

    @GetMapping("trip/{id}")
    public TripOutputDto getTripById(@PathVariable Integer id);



}
