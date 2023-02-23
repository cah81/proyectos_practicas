package com.backend.front.ticket.application;

import com.backend.front.client.infraestructure.dto.ClientOutputDto;
import com.backend.front.ticket.infraestructure.dto.*;

import java.util.List;

public interface TicketService {
    public Object createTicket(Long idPassenger,Integer idTrip);
    public List<TicketOutputDto> getAllTicket();
    public TicketOutputDto findTicketById(Long id);
    public void deleteTicket(Long id);

    public ClientOutputDto getClient(Long id);
}
