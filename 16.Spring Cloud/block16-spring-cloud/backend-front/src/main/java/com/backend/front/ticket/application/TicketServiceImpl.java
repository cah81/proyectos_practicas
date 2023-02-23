package com.backend.front.ticket.application;




import com.backend.front.client.domain.Client;
import com.backend.front.client.feign.FeignBack;
import com.backend.front.client.infraestructure.dto.ClientOutputDto;

import com.backend.front.exception.EntityNotFoundException;
import com.backend.front.ticket.domain.Ticket;
import com.backend.front.ticket.infraestructure.dto.TicketOutputDto;
import com.backend.front.ticket.infraestructure.repository.*;


import com.backend.front.trip.domain.Trip;
import com.backend.front.trip.infraestructure.dto.TripOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
      private final TicketRepository ticketRepo;

   @Autowired
   FeignBack feignBack;




    @Override
    public TicketOutputDto createTicket(Long idPassenger,Integer idTrip){
        ClientOutputDto clientOutputDto = feignBack.showById(idPassenger);
        Client client = new Client(clientOutputDto);
        TripOutputDto tripOutputDto = feignBack.getTripById(idTrip);
        Trip trip = new Trip(tripOutputDto);
        Ticket ticket = new Ticket(client,trip);
        ticketRepo.save(ticket);
        return new TicketOutputDto(ticket);

    }



    @Override
    public List<TicketOutputDto> getAllTicket() {

        return ticketRepo.findAll().stream().map(TicketOutputDto::new).toList();


    }

    @Override
    public TicketOutputDto findTicketById(Long id) {
        return new TicketOutputDto(ticketRepo.findById(id).orElseThrow(()->new EntityNotFoundException("does not exist",404,new Date())));
    }

    @Override
    public void deleteTicket(Long id) {

    }

    @Override
    public ClientOutputDto getClient(Long id) {

        //return clientServiceFeign.getClientById(id);
        return null;
        }
    }

