package com.example.transport.models;

import jakarta.persistence.*;

@Entity
@Table(name = "transport_buses")
public class Bus {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String model;
    private Integer capacity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
