package com.example.transport.repositories;

import com.example.transport.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository  extends JpaRepository<Bus, Integer> {
}
