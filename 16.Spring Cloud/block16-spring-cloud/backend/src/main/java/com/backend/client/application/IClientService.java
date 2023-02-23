package com.backend.client.application;

import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.client.infraestructure.dto.ClientOutputDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClientService {

    //create to client
    public ClientOutputDto createClient(ClientInputDto clientInputDto);

    //update client

    public ClientOutputDto updateClient(ClientInputDto clientInputDto,Long id);

    //delete client

    public String deleteClient(Long id);

    //Search client by id

    public ClientOutputDto findByIdClient(Long id);

    //list client

    public List<ClientOutputDto> listClient();

    //list Client Page
  //  public Page<ClientOutputDto> getData(ClientPage clientPage, SearchFilterClient searchFilterClient);
}
