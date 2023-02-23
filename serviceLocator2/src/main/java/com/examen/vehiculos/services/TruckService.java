package com.examen.vehiculos.services;

import com.examen.vehiculos.model.Vehicle;
import com.examen.vehiculos.registry.AdapterService;
import org.springframework.stereotype.Service;

@Service("truck")
public class TruckService implements AdapterService<Vehicle> {
    @Override
    public void process(Vehicle request) {
        System.out.println("inside truck service" + request.toString());

    }
}
