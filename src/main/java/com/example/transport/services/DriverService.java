package com.example.transport.services;

import com.example.transport.models.Driver;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface DriverService {
    List<Driver> getAll();
    Driver getById(Integer id);
    Driver saveOrUpdate(Driver driver) throws AlreadyBoundException;
    void deleteById(Integer id);
}
