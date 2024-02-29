package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;


//    method for displaying all flights
    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

//    method for displaying a specific flight
    public Flight getAllFlights(Long id){
        return flightRepository.findById(id).get();
    }


//    method for adding details of a new flight
    public void saveFlight(Flight flight){
        flightRepository.save(flight);
    }

}
