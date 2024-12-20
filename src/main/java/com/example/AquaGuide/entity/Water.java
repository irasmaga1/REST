package com.example.AquaGuide.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "water")
public class Water {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "area")
    private double area;

    @Column(name = "depth")
    private double depth;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @OneToMany(mappedBy = "waterBody")
    private List<Observation> observations;

    public Water(List<Observation> observations, Region region) {
        this.observations = observations;
        this.region = region;
    }

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


    public Water() {
    }

    public Water(String name, String type, double area, double depth, String location, String description) {
        this.name = name;
        this.type = type;
        this.area = area;
        this.depth = depth;
        this.location = location;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    @Override
    public String toString() {
        return "Water{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", area=" + area +
                ", depth=" + depth +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
