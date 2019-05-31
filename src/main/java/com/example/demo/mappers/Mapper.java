package com.example.demo.mappers;

public interface Mapper<F,T> {
    T map(F from);
}
