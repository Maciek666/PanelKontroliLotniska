package com.example.demo.controllers;

import com.example.demo.dto.AirportDTO;
import com.example.demo.entities.Airport;
import com.example.demo.mappers.AirportMapper;
import com.example.demo.repositories.AirportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class AirportController {

    @Autowired
    AirportRepo airportRepo;

    @RequestMapping(value = "airport/add", method = RequestMethod.POST)
    public ResponseEntity<Airport> addAirport(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "city") String city
    ) {
        Optional<Airport> airPort = airportRepo.findByName(name);
        if (!airPort.isPresent()) {
            Airport add = new Airport();
            add.setName(name);
            add.setCity(city);
            airportRepo.save(add);
            return ResponseEntity.ok().build();
        } else {
            //TODO obsługa na froncie

            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "airport/{name}/update", method = RequestMethod.PUT)
    public ResponseEntity<Airport> updateAirPort(
            @PathVariable(value = "name") String oldName,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "name", required = false) String newName
    ) {

        Optional<Airport> airPort = airportRepo.findByName(oldName);
        if (airPort.isPresent()) {

            if (city != null)
                airPort.get().setCity(city);
            if (newName != null)
                airPort.get().setName(newName);
            airportRepo.save(airPort.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "airport/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Airport> delete(@PathVariable String name) {
        Optional<Airport> optionalAirPort = airportRepo.findByName(name);
        if (optionalAirPort.isPresent()) {
            airportRepo.deleteById(optionalAirPort.get().getId());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("airports")
    public List<AirportDTO> getAirPorts(
            @RequestParam(value = "expression", required = false) String expression) {
        List<AirportDTO> AirPortsDto = new ArrayList<>();
        AirportMapper mapper = new AirportMapper();
        if (expression == null) {
            List<Airport> airPorts = airportRepo.findAll();
            for (Airport b : airPorts) {
                AirportDTO AirPortDto = mapper.map(b);
                AirPortsDto.add(AirPortDto);
            }
        } else {
            List<Airport> airPorts = airportRepo.findByExpression(expression);

            for (Airport b : airPorts) {
                AirportDTO AirPortDto = mapper.map(b);
                AirPortsDto.add(AirPortDto);
            }
        }
        return AirPortsDto;
    }

    @GetMapping("airport/{name}")
    public AirportDTO getAirport(
            @PathVariable(value = "name") String name
    ) {
        Optional<Airport> airport = airportRepo.findByName(name);
        if (airport.isPresent()) {
            return new AirportMapper().map(airport.get());
        } else return new AirportDTO();
    }

}
