package com.backend.client.application;

import com.backend.client.domain.Client;
import com.backend.client.infraestructure.dto.ClientInputDto;
import com.backend.client.infraestructure.dto.ClientOutputDto;

import com.backend.client.infraestructure.repository.ClientRepository;
import com.backend.exception.EntityNotFoundException;
import com.backend.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService{
    private final ClientRepository clientRepo;
//    @Autowired
//   ClientCriteriaRepository clientCriteriaDao;

    @Override
    public ClientOutputDto createClient(ClientInputDto clientInputDto) {
        Client clientNow = clientRepo.findClientByEmail(clientInputDto.getEmail());
        if(clientNow!=null){
            throw new UnprocessableEntityException("client already exists",422,new Date());
        }
        Client client = new Client(clientInputDto);
        clientRepo.save(client);
        return new ClientOutputDto(client);
    }

    @Override
    public ClientOutputDto updateClient(ClientInputDto clientInputDto, Long id) {
        Client client;
        Optional<Client> clientOptional = clientRepo.findById(id);
        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException("Client does not exist",404,new Date());
        }
        client = clientOptional.get();
        client.setName(clientInputDto.getName());
        client.setSurname(clientInputDto.getSurname());
        client.setAge(clientInputDto.getAge());
        client.setEmail(clientInputDto.getEmail());
        client.setPhoneNumber(clientInputDto.getPhoneNumber());
        clientRepo.save(client);
        return new ClientOutputDto(client);




    }

    @Override
    public String deleteClient(Long id) {
        Optional<Client> clientOptional = clientRepo.findById(id);
        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException("error Client does not exist",404,new Date());
        }
        clientRepo.delete(clientOptional.get());
        return "Client deleted";
    }



    @Override
    @Transactional(readOnly = true)
    public ClientOutputDto findByIdClient(Long id) {
        return new ClientOutputDto(clientRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Client does not exist",404,new Date())));
    }

    @Override
    public List<ClientOutputDto> listClient() {
        List<ClientOutputDto> clientOutputDtoList = new ArrayList<>();
        clientRepo.findAll().forEach(client -> {
            ClientOutputDto clientOutputDto = new ClientOutputDto(client);
            clientOutputDtoList.add(clientOutputDto);
        });
        return clientOutputDtoList;
    }


}
