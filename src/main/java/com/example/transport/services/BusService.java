package com.example.transport.services;

import com.example.transport.models.Bus;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface BusService {
    List<Bus> getAll();
    Bus getById(Integer id);
    Bus saveOrUpdate(Bus bus) throws AlreadyBoundException;
    void deleteById(Integer id);
}
