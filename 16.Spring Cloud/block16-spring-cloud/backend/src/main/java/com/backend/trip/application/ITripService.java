package com.backend.trip.application;

import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.trip.infraestructure.dto.TripOutputDto;

import java.util.List;

public interface ITripService {
     public  TripOutputDto createTrip(TripInputDto tripInputDto);

     public TripOutputDto updateTrip(Integer id, TripInputDto tripInputDto);

     public List<TripOutputDto> getAllTrip();

     public String deleteTrip(Integer id);

     public TripOutputDto getTripByID(Integer id);

     public Integer countPassengers(Integer trip_id);

     public TripOutputDto addPassenger(Integer idTrip, Long idClient);

     public TripOutputDto changeStatus(Integer idTrip,String status);

     public String checkStatus(Integer idTrip);


}
