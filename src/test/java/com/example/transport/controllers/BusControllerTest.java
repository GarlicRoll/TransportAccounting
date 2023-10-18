package com.example.transport.controllers;

import com.example.transport.models.Bus;
import com.example.transport.services.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BusControllerTest {

    private BusService busService;
    private BusController busController;

    @BeforeEach
    void setUp() {
        // This creates a mock of the service
        busService = mock(BusService.class);

        busController = new BusController();

        // Add driverService to the controller manually
        busController.busService = busService;
    }

    @Test
    void list() {
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();

        // Isolating system, not involving real dependencies (not execution)
        when(busService.getAll()).thenReturn(Arrays.asList(bus1, bus2));

        // Using isolated method (bus controller uses getAll) (execute getAll)
        List<Bus> buses = busController.list();

        // Checking (getAll is not execute)
        verify(busService, times(1)).getAll();
        assertEquals(2, buses.size());
    }

}
