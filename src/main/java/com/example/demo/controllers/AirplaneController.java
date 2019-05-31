package com.example.demo.controllers;

import com.example.demo.dto.AirplaneDTO;
import com.example.demo.dto.AirportDTO;
import com.example.demo.entities.Airplane;
import com.example.demo.entities.Airport;
import com.example.demo.mappers.AirplaneMapper;
import com.example.demo.mappers.AirportMapper;
import com.example.demo.repositories.AirplaneRepo;
import com.example.demo.repositories.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class AirplaneController {
    @Autowired
    AirplaneRepo airplaneRepo;
    @Autowired
    AirportRepo airportRepo;

    @RequestMapping(value = "airplane/add", method = RequestMethod.POST)
    public ResponseEntity<String> addAirPlane(
            @RequestParam(value = "plane_model") String planeModel,
            @RequestParam(value = "number_of_seats") int numberOfSeats,
            @RequestParam(value = "serialNo") String serialNo,
            @RequestParam(value = "currentAirport", required = false) String currentAirport
    ) {
        Optional<Airplane> airPlane = airplaneRepo.findBySerialNo(serialNo);
        if (!airPlane.isPresent()) {
            Airplane add = new Airplane();
            add.setSerialNo(serialNo);
            add.setPlaneModel(planeModel);
            add.setNumberOfSeats(numberOfSeats);
            if (currentAirport != null) {
                Optional<Airport> ap = airportRepo.findByName(currentAirport);
                ap.ifPresent(add::setCurrentAirport);
            }
            airplaneRepo.save(add);
            return new ResponseEntity<String>("saved",HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("not saved",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "airplane/{serial_no}/update", method = RequestMethod.PUT)
    public ResponseEntity<Airplane> updateAirPlane(
            @PathVariable(value = "serial_no") String serialNo,
            @RequestParam(value = "plane_model") String planeModel,
            @RequestParam(value = "number_of_seats") int numberOfSeats,
            @RequestParam(value = "current_airport", required = false) String currentAirport //name
    ) {
        if (currentAirport == null)
            currentAirport = "";

        Optional<Airplane> optionalAirPlane = airplaneRepo.findBySerialNo(serialNo);
        Optional<Airport> airport = airportRepo.findByName(currentAirport);

        if (optionalAirPlane.isPresent()) {
            optionalAirPlane.get().setPlaneModel(planeModel);
            optionalAirPlane.get().setNumberOfSeats(numberOfSeats);
            optionalAirPlane.get().setCurrentAirport(null);
            airport.ifPresent(a -> optionalAirPlane.get().setCurrentAirport(a));

            airplaneRepo.save(optionalAirPlane.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "airplane/{serialNo}", method = RequestMethod.DELETE)
    public ResponseEntity<Airplane> delete(@PathVariable String serialNo) {
        Optional<Airplane> airPlane = airplaneRepo.findBySerialNo(serialNo);
        if (airPlane.isPresent()) {
            airplaneRepo.delete(airPlane.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("airplanes")
    public List<AirplaneDTO> getAirplanes() {
        List<AirplaneDTO> airplanesDto = new ArrayList<>();
        AirplaneMapper mapper = new AirplaneMapper();
        List<Airplane> airplanes = airplaneRepo.findAll();
        for (Airplane a : airplanes) {
            airplanesDto.add(mapper.map(a));
        }
        return airplanesDto;
    }

    @GetMapping("airplane/{serialNo}")
    public AirplaneDTO getAirplane(@PathVariable(value = "serialNo") String serialNo) {
        Optional<Airplane> airplane = airplaneRepo.findBySerialNo(serialNo);
        return airplane.isPresent() ? new AirplaneMapper().map(airplane.get()) : new AirplaneDTO();
    }


}
