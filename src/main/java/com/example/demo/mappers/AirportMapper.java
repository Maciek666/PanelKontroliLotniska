package com.example.demo.mappers;

import com.example.demo.dto.AirportDTO;
import com.example.demo.entities.Airport;

public class AirportMapper
        implements Mapper<Airport, AirportDTO>{
    @Override
    public AirportDTO map(Airport from) {
        return new AirportDTO(
                from.getName(),
                from.getCity()
        );
    }
}
