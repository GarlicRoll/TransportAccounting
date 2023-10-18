package com.example.transport.services.impl;

import com.example.transport.exceptions.NotFoundException;
import com.example.transport.models.Bus;
import com.example.transport.models.Driver;
import com.example.transport.repositories.BusRepository;
import com.example.transport.repositories.DriverRepository;
import com.example.transport.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    @Override
    public Bus getById(Integer id) {
        Optional<Bus> optionalBus = busRepository.findById(id);
        Bus bus = null;
        if (optionalBus.isPresent()) {
            bus = optionalBus.get();
        } else {
            throw new NotFoundException("Bus not found. Id: " + id);
        }
        return bus;
    }

    @Override
    public Bus saveOrUpdate(Bus bus) throws AlreadyBoundException {
        List<Bus> buses = getAll();

        // Проверка, прикреплён ли уже водитель к автобусу (если его хотят прикрепить)
        boolean busAlreadyConnected = false;
        if (bus.getDriver() != null) {
            for (Bus value: buses) {
                if (value.getDriver() != null && (Objects.equals(value.getDriver().getId(), bus.getDriver().getId()))) {
                    busAlreadyConnected = true;
                    break;
                }
            }
        }

        // Если автобус не пытаются прикрепить к двум водителям
        if (!busAlreadyConnected) {
            busRepository.save(bus);
            return bus;
        } else {
            throw new AlreadyBoundException("Driver already bound. Id: " + bus.getDriver().getId());
        }
    }

    @Override
    public void deleteById(Integer id) {
        if (busRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Bus not found. Id: " + id);
        }
        Bus bus = getById(id);
        if (bus != null) {
            Driver driver = bus.getDriver();
            if (driver != null) {
                driver.setBus(null);
                driverRepository.save(driver);
            }
        }
        busRepository.deleteById(id);
    }
}
