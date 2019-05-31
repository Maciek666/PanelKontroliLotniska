package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirplaneDTO {
    private String planeModel;
    private int numberOfSeats;
    private String serialNo;
    private String airportName;

    public AirplaneDTO(String planeModel, int numberOfSeats, String serialNo) {
        this.planeModel = planeModel;
        this.numberOfSeats = numberOfSeats;
        this.serialNo = serialNo;
    }
}
