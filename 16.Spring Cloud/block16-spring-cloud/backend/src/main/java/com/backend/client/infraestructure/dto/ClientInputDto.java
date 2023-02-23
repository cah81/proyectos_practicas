package com.backend.client.infraestructure.dto;

import com.backend.trip.domain.Trip;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientInputDto {

    private String name;
    private String surname;
    private String age;
    private String email;
    private String phoneNumber;

}
