package com.backend.trip.application;

import com.backend.client.domain.Client;
import com.backend.client.infraestructure.repository.ClientRepository;
import com.backend.exception.EntityNotFoundException;
import com.backend.exception.UnprocessableEntityException;
import com.backend.trip.domain.Trip;
import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.trip.infraestructure.dto.TripOutputDto;
import com.backend.trip.infraestructure.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements ITripService{


   private final TripRepository tripRepo;
   private final ClientRepository clientRepo;


    @Override
    public TripOutputDto createTrip(TripInputDto tripInputDto) {
        Trip trip = new Trip(tripInputDto);
        tripRepo.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    public TripOutputDto updateTrip(Integer id, TripInputDto tripInputDto) {
        Optional<Trip> optionalTrip = tripRepo.findById(id);
        if(optionalTrip.isEmpty()){
            throw new EntityNotFoundException("Trip does not found",404,new Date());
        }
        Trip trip = optionalTrip.get();
        trip.setArrivalDate(tripInputDto.getArrivalDate());
        trip.setDestination(tripInputDto.getDestination());
        trip.setOrigin(tripInputDto.getOrigin());
        trip.setDepartureDate(tripInputDto.getDepartureDate());
        trip.setStatus(tripInputDto.getStatus());
        tripRepo.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    public List<TripOutputDto> getAllTrip() {
        return tripRepo.findAll().stream().map(TripOutputDto::new).toList();
    }

    @Override
    public String deleteTrip(Integer id) {
        Optional<Trip> optionalTrip = tripRepo.findById(id);
        if(optionalTrip.isEmpty()){
            throw  new EntityNotFoundException("trip does not exist",404,new Date());
        }
        tripRepo.deleteById(id);
        return "Trip has been deleted";
    }

    @Override
    public TripOutputDto getTripByID(Integer id) {
        Trip trip = tripRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Trip does not exist",404,new Date()));
        return new TripOutputDto(trip);
    }

    @Override
    public Integer countPassengers(Integer trip_id) {
        Trip t = tripRepo.findById(trip_id)
                .orElseThrow(()->new EntityNotFoundException("No hay ningun viaje con id: ",404,new Date()));
        return t.getPassengers().size();
    }


    @Override
    public TripOutputDto addPassenger(Integer idTrip, Long idClient) {
       Trip trip = tripRepo.findById(idTrip).orElseThrow(()->new EntityNotFoundException("trip does not exist",404,new Date()));
       if(trip.getPassengers().size()==40){
           throw new UnprocessableEntityException("no passenger seats available",422,new Date());
       }
        if(trip.getPassengers().stream().filter(cliente-> Objects.equals(cliente.getIdClient(), idClient)).toList().size()==1){
            throw new UnprocessableEntityException("Customer already exists within the trip",422,new Date());
        };
        Client client = clientRepo.findById(idClient).orElseThrow(()->new EntityNotFoundException("Customer does not exist",404,new Date()));
        trip.getPassengers().add(client);
        tripRepo.save(trip);
        return new TripOutputDto(trip);

    }

    @Override
    public TripOutputDto changeStatus(Integer idTrip, String status) {
        Trip trip = tripRepo.findById(idTrip).orElseThrow(()->new EntityNotFoundException("Trip does not exist",404,new Date()));
        trip.setStatus(status);
        tripRepo.save(trip);
        return new TripOutputDto(trip);
    }

    @Override
    public String checkStatus(Integer idTrip) {
       return   tripRepo.findById(idTrip).orElseThrow(()->new EntityNotFoundException("Trip does not exist",404,new Date()))
                .getStatus();

    }
}
