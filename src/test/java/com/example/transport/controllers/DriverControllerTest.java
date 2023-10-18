package com.example.transport.controllers;

import com.example.transport.models.Driver;
import com.example.transport.services.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.rmi.AlreadyBoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DriverControllerTest {

    private DriverService driverService;
    private DriverController driverController;

    @BeforeEach
    void setUp() {
        // This creates a mock of the service
        driverService = mock(DriverService.class);

        driverController = new DriverController();

        // Add driverService to the controller manually
        driverController.driverService = driverService;
    }

    @Test
    void list() {
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        when(driverService.getAll()).thenReturn(Arrays.asList(driver1, driver2));

        List<Driver> drivers = driverController.list();

        verify(driverService, times(1)).getAll();
        assertEquals(2, drivers.size());
    }

    @Test
    void create() throws AlreadyBoundException {
        Driver driver = new Driver();
        when(driverService.saveOrUpdate(any())).thenReturn(driver);

        ResponseEntity<Driver> responseEntity = driverController.create(driver);

        verify(driverService, times(1)).saveOrUpdate(any());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(driver, responseEntity.getBody());
    }
}
