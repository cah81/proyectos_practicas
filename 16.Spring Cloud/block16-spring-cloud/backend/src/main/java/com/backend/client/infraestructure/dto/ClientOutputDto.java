package com.backend.client.infraestructure.dto;

import com.backend.client.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientOutputDto {
    private Long idClient;
    private String name;
    private String surname;
    private String age;
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

}
