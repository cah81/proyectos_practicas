package com.backend.front.client.infraestructure.dto;


import com.backend.front.client.domain.Client;
import lombok.*;

@Setter
@Getter

public class ClientOutputDto {
    private Long idClient;
    private String name;
    private String surname;
    private String age;

    public ClientOutputDto(Long idClient, String name, String surname, String age, String email, String phoneNumber) {

        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public ClientOutputDto() {
    }

    private String email;
    private String phoneNumber;

    public ClientOutputDto(Client client){
        this.idClient= client.getIdClient();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.phoneNumber = client.getPhoneNumber();

    }

    public ClientOutputDto(ClientOutputDto clientOutputDto) {
        this.idClient = clientOutputDto.getIdClient();
        this.name = clientOutputDto.getName();
        this.surname = clientOutputDto.getSurname();
        this.age = clientOutputDto.getAge();
        this.email = clientOutputDto.getEmail();
        this.phoneNumber = clientOutputDto.getPhoneNumber();
    }
}
