package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


// BALI
    Flight bali = new Flight("Bali", 99, "24th February", "14:30");
    flightRepository.save(bali);


    Passenger john = new Passenger("John", "john@email.com");
    john.addFlight(bali);
    passengerRepository.save(john);

    Passenger david = new Passenger("David", "david@email.com");
    david.addFlight(bali);
    passengerRepository.save(david);



//  NEW YORK
        Flight newYork = new Flight("New York", 162, "25th October", "16:30");
        flightRepository.save(newYork);


        Passenger sarah = new Passenger("Sarah", "sarah@email.com");
        sarah.addFlight(newYork);
        passengerRepository.save(sarah);

        Passenger amy = new Passenger("Amy", "amy@email.com");
        amy.addFlight(newYork);
        passengerRepository.save(amy);

}
}
