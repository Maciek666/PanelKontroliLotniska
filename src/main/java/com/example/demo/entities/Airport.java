package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "air_port")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "city",nullable = false,columnDefinition = "varchar(60)")
    private String city;

    @Column(name = "name",nullable = false,columnDefinition = "varchar(60)",unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airport", cascade = CascadeType.ALL)
    private List<Worker> workers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airportArrival", cascade = CascadeType.ALL)
    private List<Fly> departures = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airportDeparture", cascade = CascadeType.ALL)
    private List<Fly> arrival = new ArrayList<>();
}
