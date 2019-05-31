package com.example.demo.repositories;

import com.example.demo.entities.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AirplaneRepo
        extends JpaRepository<Airplane, Long> {
    Optional<Airplane> findBySerialNo(String serialNo);
}
