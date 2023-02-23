package com.empleados.empleados.infraestructure.controller;

import com.empleados.empleados.application.*;
import com.empleados.empleados.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoController {


    private  final CreateEmpleadoPort createEmpleadoPort;

    private final ListEmpleadosPort listEmpleadosPort;

    private final UpdateEmpleadoPort updateEmpleadoPort;

    private final DeleteEmpleadoPort deleteEmpleadoPort;

    private final GetEmpleadoByIdPort getEmpleadoByIdPort;

    @Autowired
    public EmpleadoController(CreateEmpleadoPort createEmpleadoPort, ListEmpleadosPort listEmpleadosPort, UpdateEmpleadoPort updateEmpleadoPort ,
                              DeleteEmpleadoPort deleteEmpleadoPort,GetEmpleadoByIdPort getEmpleadoByIdPort){

        this.createEmpleadoPort = createEmpleadoPort;
        this.listEmpleadosPort =  listEmpleadosPort;
        this.updateEmpleadoPort = updateEmpleadoPort;
        this.deleteEmpleadoPort = deleteEmpleadoPort;
        this.getEmpleadoByIdPort = getEmpleadoByIdPort;
    }


    @GetMapping("/empleados")
    public List<Empleado> listarTodosEmpleados(){
        return listEmpleadosPort.findAll();
    }
    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        return createEmpleadoPort.crearEmpleado(empleado);
    }

    @PutMapping("empleados/{id}")
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleado){
        return updateEmpleadoPort.updateEmpleado(id,empleado);
    }

    @DeleteMapping("empleados/{id}")
    public String deleteEmpleado(@PathVariable Long id){
        return deleteEmpleadoPort.deleteEmpleado(id);
    }

    @GetMapping("empleados/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoById(@PathVariable Long id){
        Empleado empleado = getEmpleadoByIdPort.findEmpleadoById(id);
        return ResponseEntity.ok(empleado);
    }


}
