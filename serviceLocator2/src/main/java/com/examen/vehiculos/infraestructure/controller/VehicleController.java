package com.examen.vehiculos.infraestructure.controller;

import com.examen.vehiculos.model.Vehicle;

import com.examen.vehiculos.registry.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private ServiceRegistry serviceRegistry;

    @PostMapping
    public void processDetails(@RequestBody Vehicle vehicle) {
        serviceRegistry.getService(vehicle.getVehicleType()).process(vehicle);
    }
}
