package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.PassengerRepository;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    PassengerRepository passengerRepository;



    // Display details of all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers(){
        return new ResponseEntity<>(passengerService.getAllPassengers(), HttpStatus.OK);
    }

    // Display specific passenger details
    @GetMapping(value = "/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id){
        return new ResponseEntity<>(passengerService.getPassengerById(id), HttpStatus.OK);
    }

    // Add a new passenger
    @PostMapping
    public ResponseEntity<List<Passenger>> addNewPassenger(@RequestBody Passenger passenger){
        passengerRepository.save(passenger);
        return new ResponseEntity<>(passengerRepository.findAll(), HttpStatus.CREATED);
    }

}
