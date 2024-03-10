package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService flightService;

    @Autowired
    FlightRepository flightRepository;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        return new ResponseEntity<>(flightService.findAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        return new ResponseEntity<>(flightService.getAllFlights(id), HttpStatus.OK);
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<List<Flight>> postFlight(@RequestBody Flight flight){
        flightRepository.save(flight);
        return new ResponseEntity<>(flightRepository.findAll(), HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable Long id, @RequestBody List<Long> passengerIds) {
        Flight flight = flightService.addPassengerToFlight(id, passengerIds);
        return new ResponseEntity<>(flight, HttpStatus.OK);
        }



    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
