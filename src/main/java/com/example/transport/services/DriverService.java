package com.example.transport.services;

import com.example.transport.models.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAll();
    Driver getById(Integer id);
    void saveOrUpdate(Driver driver);
    void deleteById(Integer id);
}
