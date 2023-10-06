package com.example.transport.controllers;

import com.example.transport.models.Bus;
import com.example.transport.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/buses")
public class BusController {

    @Autowired
    BusService busService;

    @GetMapping
    public List<Bus> list() {

        List<Bus> buses = busService.getAll();

        return buses;
    }
}
