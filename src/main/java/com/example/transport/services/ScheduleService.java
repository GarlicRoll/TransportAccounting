package com.example.transport.services;

import com.example.transport.models.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAll();
    Schedule getById(Integer id);
    void saveOrUpdate(Schedule schedule);
    void deleteById(Integer id);
}
