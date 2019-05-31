package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "fly")
public class Fly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //wylot
    @Column(name = "departure_data",nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime departureDate;
    //przylot
    @Column(name = "arrival_data",nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime arrivalDate;
    @Column(name = "tookPlace",nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean tookPlace;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "fk_departure")
    private Airport airportDeparture;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "fk_arrival")
    private Airport airportArrival;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "fk_airplane")
    private Airplane airplane;

}
