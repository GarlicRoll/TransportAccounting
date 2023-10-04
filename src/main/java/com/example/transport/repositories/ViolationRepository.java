package com.example.transport.repositories;

import com.example.transport.models.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationRepository extends JpaRepository<Violation, Integer> {
}
