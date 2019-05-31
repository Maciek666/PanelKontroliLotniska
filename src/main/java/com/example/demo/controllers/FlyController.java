package com.example.demo.controllers;

import com.example.demo.dto.FlyDTO;
import com.example.demo.entities.Airplane;
import com.example.demo.entities.Airport;
import com.example.demo.entities.Fly;
import com.example.demo.mappers.FlyMapper;
import com.example.demo.repositories.AirplaneRepo;
import com.example.demo.repositories.AirportRepo;
import com.example.demo.repositories.FlyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class FlyController {

    @Autowired
    AirplaneRepo airplaneRepo;
    @Autowired
    AirportRepo airportRepo;
    @Autowired
    FlyRepo flyRepo;

    @RequestMapping(value = "fly/add", method = RequestMethod.POST)
    public ResponseEntity<Fly> addFly(
            @RequestParam(value = "departure_date") String departureDate,
            @RequestParam(value = "arrival_date") String arrivalDate,
            @RequestParam(value = "arrival_air_port") String Arrival,
            @RequestParam(value = "departure_airport") String Departure,
            @RequestParam(value = "plane") String serialNo
    ) {
        Fly add = new Fly();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            System.out.println("\n\n\n\n" + departureDate);
            System.out.println("\n\n\n\n" + arrivalDate);
            System.out.println("\n\n\n\n");
            if (!departureDate.equals("") && !arrivalDate.equals("")) {
                LocalDateTime aD = LocalDateTime.parse(arrivalDate, formatter);//.plusHours(2);
                LocalDateTime dD = LocalDateTime.parse(departureDate, formatter);//.plusHours(2);
                add.setArrivalDate(aD);
                add.setDepartureDate(dD);
            }
        } catch (java.time.format.DateTimeParseException e) {
        }
        //add.setDepartureDate(departureDate);
        Optional<Airplane> plane = airplaneRepo.findBySerialNo(serialNo);
        Optional<Airport> departure = airportRepo.findByName(Departure);
        Optional<Airport> arrival = airportRepo.findByName(Arrival);

        plane.ifPresent(add::setAirplane);
        departure.ifPresent(add::setAirportDeparture);
        arrival.ifPresent(add::setAirportArrival);

        if (plane.isPresent() && departure.isPresent() && arrival.isPresent()) {
            if (departure.get().getId() != arrival.get().getId()) {
                if (add.getArrivalDate() != null && add.getDepartureDate() != null) {
                    if (add.getDepartureDate().isBefore(add.getArrivalDate()) && add.getDepartureDate().plusDays(1).isAfter(add.getArrivalDate())) {
                        flyRepo.save(add);
                        return ResponseEntity.ok().build();
                    }
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }


    @RequestMapping(value = "fly/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<Fly> updateFly(
            @PathVariable(value = "id") Long id,
            @RequestParam(value = "departure_date", required = false) String departureDate,
            @RequestParam(value = "arrival_date", required = false) String arrivalDate,
            @RequestParam(value = "took_place", required = false) Boolean tookPlace,
            @RequestParam(value = "airplane", required = false) String serialNo

    ) {

        Optional<Fly> optionalFly = flyRepo.findById(id);

        Optional<Airplane> optionalAirplane = airplaneRepo.findBySerialNo(serialNo);

        if (optionalFly.isPresent()) {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            if (arrivalDate != null) {
                LocalDateTime aD = LocalDateTime.parse(arrivalDate, formatter);
                optionalFly.get().setArrivalDate(aD);
            }
            if (departureDate != null) {
                LocalDateTime dD = LocalDateTime.parse(departureDate, formatter);
                optionalFly.get().setDepartureDate(dD);
            }
            if (tookPlace != null)
                optionalFly.get().setTookPlace(tookPlace);

            optionalAirplane.ifPresent(a -> optionalFly.get().setAirplane(a));

            flyRepo.save(optionalFly.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "fly/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Fly> deleteFly(
            @PathVariable(value = "id") Long id
    ) {

        Optional<Fly> optionalFly = flyRepo.findById(id);
        if (optionalFly.isPresent()) {
            flyRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("flights")
    public List<FlyDTO> getFlights() {
        FlyMapper mapper = new FlyMapper();
        return flyRepo.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @GetMapping("flight")
    public FlyDTO getFly(
            @PathVariable Long id
    ) {
        Optional<Fly> fly = flyRepo.findById(id);
        if (fly.isPresent())
            return new FlyMapper().map(fly.get());
        else return new FlyDTO();
    }
}
