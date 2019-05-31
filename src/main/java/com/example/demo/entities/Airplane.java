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
@Table(name = "air_plane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "plane_model",columnDefinition = "varchar(40) NOT NULL")
    private String planeModel;

    @Column(name = "number_of_seats",columnDefinition = "INT",nullable = false)
    private int numberOfSeats;

    //numer seryjny samolotu
    @Column(name = "serial_no",columnDefinition = "varchar(20)",nullable = false)
    private String serialNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_current_airport")
    private Airport currentAirport;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airplane", cascade = CascadeType.ALL)
    private List<Fly> flies = new ArrayList<>();

}
