package com.examen.vehiculos.services;

import com.examen.vehiculos.model.Vehicle;
import com.examen.vehiculos.registry.AdapterService;
import org.springframework.stereotype.Service;

@Service("bus")
public class BusService implements AdapterService<Vehicle> {
    @Override
    public void process(Vehicle request) {
        System.out.println("inside bus service" + request.toString());
    }
}
