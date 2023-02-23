package com.backend.front.trip.infraestructure.dto;

import com.backend.front.client.infraestructure.dto.ClientOutputDto;


import com.backend.front.trip.domain.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripOutputDto {
    private Long id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;

    private String status;

    //private String passenger;

    private List<ClientOutputDto> listPassengers = new ArrayList<>();

    public TripOutputDto(Trip trip){
        this.id = trip.getId();
       // this.passenger = trip.getPassenger();
        this.origin = trip.getOrigin();
        this.arrivalDate = trip.getArrivalDate();
        this.departureDate = trip.getDepartureDate();
        this.listPassengers = trip.getPassengers().stream().map(ClientOutputDto::new).toList();
        this.status = trip.getStatus();
        this.destination = trip.getDestination();



    }




    }

