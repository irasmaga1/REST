package com.example.AquaGuide.entity;

import jakarta.persistence.*;

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

    public Water() {
    }

    public Water(Long id, String name, String type, double area, double depth, String location, String description) {
        this.id = id;
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
