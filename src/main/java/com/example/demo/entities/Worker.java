package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false,columnDefinition = "varchar(40)")
    private String name;
    @Column(name = "surname",nullable = false,columnDefinition = "varchar(40)")
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_airport")
    private Airport airport;
}
