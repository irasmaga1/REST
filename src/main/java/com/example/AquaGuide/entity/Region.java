package com.example.AquaGuide.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private int population;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "region")
    private List<Observation> observations;

    @OneToMany(mappedBy = "region")
    private List<Water> waterBodies;



    public Region(String name, int population, String description) {
        this.name = name;
        this.population = population;
        this.description = description;
    }

    public Region() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
