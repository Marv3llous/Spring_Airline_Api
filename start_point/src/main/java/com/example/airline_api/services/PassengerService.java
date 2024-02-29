package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.NewPassengerDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {


    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    FlightRepository flightRepository;

// method to add a passenger to a flight
    public Passenger savePassenger(NewPassengerDTO newPassengerDTO){
        Passenger passenger = new Passenger((newPassengerDTO.getName()),newPassengerDTO.getEmail());
        for(Long flightId : newPassengerDTO.getFlightIds()){
            Flight flight = flightRepository.findById(flightId).get();
            passenger.addFlight(flight);
        }
        return passengerRepository.save(passenger);
    }



//Method to display all passengers
    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

// method to display specific passengers by id
    public Passenger getPassengerById(Long id){
        return passengerRepository.findById(id).get();
    }

// method to add new passenger
    public void addNewPassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }


}
