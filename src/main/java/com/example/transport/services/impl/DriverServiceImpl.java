package com.example.transport.services.impl;

import com.example.transport.models.Driver;
import com.example.transport.repositories.DriverRepository;
import com.example.transport.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getById(Integer id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        Driver driver = null;
        if (optionalDriver.isPresent()) {
            driver = optionalDriver.get();
        }
        return driver;
    }

    @Override
    public void saveOrUpdate(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteById(Integer id) {
        driverRepository.deleteById(id);
    }
}
