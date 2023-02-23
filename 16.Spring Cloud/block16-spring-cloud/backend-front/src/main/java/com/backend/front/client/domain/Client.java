package com.backend.front.client.domain;

import com.backend.front.client.infraestructure.dto.ClientInputDto;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import com.backend.front.trip.domain.Trip;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class Client {

    private Long idClient;


    private String name;

    private String surname;

    private String age;

    private String email;

    private String phoneNumber;


    private List<Trip> tripList;

    public Client (ClientOutputDto clientOutputDto){
        this.name = clientOutputDto.getName();
        this.surname = clientOutputDto.getSurname();
        this.age = clientOutputDto.getAge();
        this.email = clientOutputDto.getEmail();
        this.phoneNumber = clientOutputDto.getPhoneNumber();
    }

}