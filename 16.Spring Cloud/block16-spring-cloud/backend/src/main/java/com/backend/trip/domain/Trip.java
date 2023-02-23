package com.backend.trip.domain;

import com.backend.client.domain.Client;


import com.backend.trip.infraestructure.dto.TripInputDto;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrip")
    private Integer idTrip;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String passenger;
    private String status;
    @JoinTable(
            name = "rel_client_trip",
            joinColumns = @JoinColumn(name = "fk_trip", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_client", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames={"fk_trip", "fk_client"})
    )
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private List<Client> passengers = new ArrayList<>();


    public Trip(String origin, String destination, Date departureDate, Date arrivalDate, String passenger, String status, List<Client> passengers) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passenger = passenger;
        this.status = status;
        this.passengers = passengers;
    }

    public Trip(TripInputDto tripInputDto){
        this.origin = tripInputDto.getOrigin();
        this.destination = tripInputDto.getDestination();
        this.departureDate = tripInputDto.getDepartureDate();
        this.arrivalDate = tripInputDto.getArrivalDate();
        this.passenger = tripInputDto.getPassenger();
        this.status = tripInputDto.getStatus();


    }

}
