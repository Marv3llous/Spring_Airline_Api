package com.example.airline_api.services;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.NewPassengerDTO;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id).get();
        for(Passenger passenger : flight.getPassengers()){
            passenger.removeFlight(flight);
            passengerRepository.save(passenger);
        }
        flightRepository.delete(flight);
    }

    public Flight addPassengerToFlight(Long passengerId, List<Long> flightIds) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
        if (optionalPassenger.isPresent()) {
            Passenger passenger = optionalPassenger.get();

            for (Long flightId : flightIds) {
                Optional<Flight> optionalFlight = flightRepository.findById(flightId);
                if (optionalFlight.isPresent()) {
                    Flight flight = optionalFlight.get();
                    passenger.addFlight(flight);
                } else {
                    return null;
                }
            }
            passengerRepository.save(passenger);
        } else {
            return null;
        }
        return null;
    }


}
