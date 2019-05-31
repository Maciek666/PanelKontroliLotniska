package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlyDTO {
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private boolean tookPlace;
    private String airportDeparture; //name
    private String airportArrival;   //name
    private String airplane;         //serialNo - VIN
    private Long flyID;
}
