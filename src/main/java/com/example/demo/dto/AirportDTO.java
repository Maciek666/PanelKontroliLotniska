package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AirportDTO {

    private String name;
    private String city;

    @Override
    public String toString() {
        return "AirportDTO{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
