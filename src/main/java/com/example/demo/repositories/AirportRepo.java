package com.example.demo.repositories;

import com.example.demo.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AirportRepo
        extends JpaRepository<Airport, Long> {
    String FIND_BY_EXPRESSION = "select * from air_port where city like ?1% or name like ?1%";
    @Query(value = FIND_BY_EXPRESSION, nativeQuery = true)
    List<Airport> findByExpression(String expression);


    Optional<Airport> findByName(String name);

}
