package com.example.transport.services;

import com.example.transport.models.Violation;

import java.util.List;

public interface ViolationService {
    List<Violation> getAll();
    Violation getById(Integer id);
    void saveOrUpdate(Violation violation);
    void deleteById(Integer id);
}
