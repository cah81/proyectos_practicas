package com.backend.front.trip.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripInputDto {
    private Long id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String passenger;
    private String status;
}
