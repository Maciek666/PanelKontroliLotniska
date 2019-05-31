package com.example.demo.mappers;

import com.example.demo.dto.WorkerDTO;
import com.example.demo.entities.Worker;

public class WorkerMapper
        implements Mapper<Worker, WorkerDTO> {
    @Override
    public WorkerDTO map(Worker from) {
        return new WorkerDTO(
                from.getName(),
                from.getSurname()
        );
    }
}
