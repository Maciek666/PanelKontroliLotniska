package com.example.demo.mappers;

import com.example.demo.dto.AirplaneDTO;
import com.example.demo.entities.Airplane;

public class AirplaneMapper
        implements Mapper<Airplane, AirplaneDTO> {

    @Override
    public AirplaneDTO map(Airplane from) {
        if (from.getCurrentAirport() == null)
            return new AirplaneDTO(
                    from.getPlaneModel(),
                    from.getNumberOfSeats(),
                    from.getSerialNo(),
                    "brak"
                    );
        else return new AirplaneDTO(
                from.getPlaneModel(),
                from.getNumberOfSeats(),
                from.getSerialNo(),
                from.getCurrentAirport().getName()
        );

    }
}
