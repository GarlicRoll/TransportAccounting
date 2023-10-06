package com.example.transport.controllers;

import com.example.transport.models.Driver;
import com.example.transport.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
