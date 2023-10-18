package com.example.transport.services.impl;

import com.example.transport.exceptions.NotFoundException;
import com.example.transport.models.Bus;
import com.example.transport.models.Driver;
import com.example.transport.repositories.BusRepository;
import com.example.transport.repositories.DriverRepository;
import com.example.transport.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private BusRepository busRepository;

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
        } else {
            throw new NotFoundException("Driver not found. Id: " + id);
        }
        return driver;
    }

    @Override
    public Driver saveOrUpdate(Driver driver) throws AlreadyBoundException {
        List<Driver> drivers = getAll();

        // Проверка, прикреплён ли уже автобус к водителю (если его хотят прикрепить)
        boolean driverAlreadyConnected = false;
        if (driver.getBus() != null) {
            for (Driver value: drivers) {
                if (value.getBus() != null && (Objects.equals(value.getBus().getId(), driver.getBus().getId()))) {
                    driverAlreadyConnected = true;
                    break;
                }
            }
        }

        // Если водителя не пытаются прикрепить к двум автобуса
        if (!driverAlreadyConnected) {
            driverRepository.save(driver);
            return driver;
        } else {
            throw new AlreadyBoundException("Bus already bound. Id: " + driver.getBus().getId());
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (driverRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Driver not found. Id: " + id);
        }
        Driver driver = getById(id);
        if (driver != null) {
            Bus bus = driver.getBus();
            if (bus != null) {
                bus.setDriver(null);
                busRepository.save(bus);
            }
        }
        driverRepository.deleteById(id);
    }
}
