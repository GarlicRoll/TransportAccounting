package com.example.transport.repositories;

import com.example.transport.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
