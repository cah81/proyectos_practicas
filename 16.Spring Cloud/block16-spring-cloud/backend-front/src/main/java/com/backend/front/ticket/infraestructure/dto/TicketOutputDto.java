package com.backend.front.ticket.infraestructure.dto;


import com.backend.front.ticket.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketOutputDto {
    Long id;
    //Long passengerId;
    String passengerName;
    String passengerLastName;
    String passengerEmail;
    String tripOrigin;
    String tripDestination;
    Date departureDate;
    Date arrivalDate;

public TicketOutputDto(Ticket ticket){
        this.id = ticket.getId();
     //   this.passengerId = ticket.getId();
        this.passengerName= ticket.getPassengerName();
        this.passengerLastName = ticket.getPassengerLastName();
        this.passengerEmail = ticket.getPassengerEmail();
        this.tripOrigin = ticket.getTripOrigin();
       this.tripDestination = ticket.getTripDestination();
        this.arrivalDate = ticket.getArrivalDate();
        this.departureDate = ticket.getDepartureDate();
    }

}
