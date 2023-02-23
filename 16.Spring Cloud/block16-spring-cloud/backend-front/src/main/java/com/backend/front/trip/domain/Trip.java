package com.backend.front.trip.domain;

import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import com.backend.front.trip.infraestructure.dto.TripInputDto;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
   private  Long id;
   private String origin;
   private String destination;
   private Date departureDate;
   private Date arrivalDate;
   private  String status;

   private String passenger;

    private List<ClientOutputDto> passengers = new ArrayList<>();

    public Trip(String origin, String destination, Date departureDate, Date arrivalDate, String passenger, String status, List<ClientOutputDto> passengers) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passenger = passenger;
        this.status = status;
        this.passengers = passengers;
    }

    public Trip(TripOutputDto tripOutputDto){
        this.origin = tripOutputDto.getOrigin();
        this.destination = tripOutputDto.getDestination();
        this.departureDate = tripOutputDto.getDepartureDate();
        this.arrivalDate = tripOutputDto.getArrivalDate();
      //  this.passenger = tripOutputDto.getPassenger();
        this.status = tripOutputDto.getStatus();


    }
}
