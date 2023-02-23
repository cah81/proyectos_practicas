package com.backend.client.infraestructure.controller;

import com.backend.client.application.IClientService;
import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.client.infraestructure.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    IClientService clientService;


    @PostMapping("/addclient")
    public ClientOutputDto addPerson(@RequestBody ClientInputDto clientInputDto) throws Exception {
        return clientService.createClient(clientInputDto);
    }
    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) throws Exception {
        clientService.deleteClient(id);
        return "User deleted";
    }

    @GetMapping("/getall")
    public List<ClientOutputDto> findall(){
        return clientService.listClient();
    }

    @GetMapping("/{id}")
    public ClientOutputDto showById(@PathVariable Long id) throws Exception {
        return clientService.findByIdClient(id);
    }
    @PutMapping("/update/{id}")
    public ClientOutputDto updatePerson(@PathVariable Long id, @RequestBody ClientInputDto clientInputDto) throws Exception {
        return clientService.updateClient(clientInputDto,id);
    }

}
