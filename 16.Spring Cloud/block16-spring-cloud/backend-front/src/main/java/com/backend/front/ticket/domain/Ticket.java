package com.backend.front.ticket.domain;

import com.backend.front.client.domain.Client;
import com.backend.front.trip.domain.Trip;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import lombok.*;

import javax.persistence.*;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long passengerId;
    private String passengerName;
    private String passengerLastName;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;




    public Ticket(Client client, Trip trip){
        this.passengerId = client.getIdClient();
        this.passengerName = client.getName();
        this.passengerLastName = client.getSurname();
        this.passengerEmail = client.getEmail();
        this.tripOrigin = trip.getOrigin();
        this.tripDestination = trip.getDestination();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();


    }

}
