package com.example.transport.controllers;

import com.example.transport.models.Bus;
import com.example.transport.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    BusService busService;

    @GetMapping
    public List<Bus> list() {

        List<Bus> buses = busService.getAll();

        return buses;
    }
}
