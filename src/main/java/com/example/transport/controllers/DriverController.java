package com.example.transport.controllers;

import com.example.transport.models.Driver;
import com.example.transport.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.AlreadyBoundException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping
    public List<Driver> list() {

        List<Driver> drivers = driverService.getAll();
        return drivers;
    }

    @PostMapping
    public ResponseEntity<Driver> create(@RequestBody Driver driver) throws AlreadyBoundException {
        driverService.saveOrUpdate(driver);
        return new ResponseEntity<>(driver, HttpStatus.valueOf(201));
    }
}
