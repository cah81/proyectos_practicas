package com.examen.vehiculos.services;

import com.examen.vehiculos.model.Vehicle;
import com.examen.vehiculos.registry.AdapterService;
import org.springframework.stereotype.Service;

@Service("bike")
public class BikeService implements AdapterService<Vehicle> {
    @Override
    public void process(Vehicle request) {
        System.out.println("inside bike service - "+request.toString() );
    }
}

