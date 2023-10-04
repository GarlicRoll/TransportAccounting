package com.example.transport.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transport_violations")
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private LocalDateTime violationTime;

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable=false)
    private Schedule schedule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(LocalDateTime violationTime) {
        this.violationTime = violationTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
