package com.example.transport.services;

import com.example.transport.models.Bus;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface BusService {
    List<Bus> getAll();
    Bus getById(Integer id);
    void saveOrUpdate(Bus bus) throws AlreadyBoundException;
    void deleteById(Integer id);
}
