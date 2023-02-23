package com.backend.trip.infraestructure.controller;

import com.backend.trip.application.ITripService;
import com.backend.trip.infraestructure.dto.TripInputDto;
import com.backend.trip.infraestructure.dto.TripOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final ITripService tripService;

    @PostMapping()
    public TripOutputDto addTrip(@RequestBody TripInputDto tripInputDto){
        return tripService.createTrip(tripInputDto);

    }

    @PutMapping()
    public TripOutputDto updateTrip(@PathVariable Integer id,@RequestBody TripInputDto tripInputDto){
        return tripService.updateTrip(id,tripInputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Integer id){
        tripService.deleteTrip(id);
        return new ResponseEntity<>("trip has been deleted", HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public TripOutputDto getTripById(@PathVariable Integer id){
        return tripService.getTripByID(id);
    }

    @GetMapping("/listTrip")
    public List<TripOutputDto> getTripList(){
        return tripService.getAllTrip();

    }

    @PutMapping("/addPassenger/{idTrip}/{idClient}")
    public TripOutputDto getPassenger(@PathVariable Integer idTrip,@PathVariable Long idClient){
        return tripService.addPassenger(idTrip,idClient);
    }

    @GetMapping("/passenger/count/{id}")
    public ResponseEntity<String> countPassengers(@PathVariable Integer id){
        return new ResponseEntity<>("Numero de pasajeros del viaje con id " + id
                + ": " + tripService.countPassengers(id), HttpStatus.OK);
    }

    @GetMapping("/trip/{trip_id}/{status}")
    public TripOutputDto changeStatus(@PathVariable Integer trip_id, @PathVariable String status){
        return tripService.changeStatus(trip_id,status);
    }

    @GetMapping("/trip/verify/{idTrip}")
    public ResponseEntity<?> verifyStatus(@PathVariable("idTrip") Integer idTrip){
        return ResponseEntity.ok().body(tripService.checkStatus(idTrip));
    }


}
