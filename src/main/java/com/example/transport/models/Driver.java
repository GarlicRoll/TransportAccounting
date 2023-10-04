package com.example.transport.models;

import jakarta.persistence.*;

@Entity
@Table(name = "transport_drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "experience")
    private int experience;

    @Column(name = "license_class")
    private String licenseClass;

    @OneToOne(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Bus bus;

    // Default constructor
    public Driver() {}

    // Parameterized constructor
    public Driver(String name, int experience, String licenseClass) {
        this.name = name;
        this.experience = experience;
        this.licenseClass = licenseClass;
    }

    // Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getLicenseClass() {
        return this.licenseClass;
    }

    public void setLicenseClass(String licenseClass) {
        this.licenseClass = licenseClass;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}