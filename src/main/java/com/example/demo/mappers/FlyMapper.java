package com.example.demo.mappers;

import com.example.demo.dto.FlyDTO;
import com.example.demo.entities.Fly;

public class FlyMapper
        implements Mapper<Fly, FlyDTO> {
    @Override
    public FlyDTO map(Fly from) {
        return new FlyDTO(
                from.getDepartureDate(),
                from.getArrivalDate(),
                from.isTookPlace(),
                from.getAirportDeparture().getName(),
                from.getAirportArrival().getName(),
                from.getAirplane().getSerialNo(),
                from.getId()
        );
    }
}
