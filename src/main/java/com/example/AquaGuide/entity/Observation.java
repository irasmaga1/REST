package com.example.AquaGuide.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "observer_name")
    private String observerName;

    @Column(name = "observe_date")
    private String observeDate;

    @Column(name = "ph_level")
    private double phLevel;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "water_quality")
    private String waterQuality;

    @Column(name = "wildlife_present")
    private String wildlifePresent;


    public Observation(Long id, String observerName, String observeDate, double phLevel, double temperature, String waterQuality, String wildlifePresent) {
        this.id = id;
        this.observerName = observerName;
        this.observeDate = observeDate;
        this.phLevel = phLevel;
        this.temperature = temperature;
        this.waterQuality = waterQuality;
        this.wildlifePresent = wildlifePresent;
    }

    public Observation() {
    }

    public String getObserverName() {
        return observerName;
    }

    public void setObserverName(String observerName) {
        this.observerName = observerName;
    }

    public String getObserveDate() {
        return observeDate;
    }

    public void setObserveDate(String observeDate) {
        this.observeDate = observeDate;
    }

    public double getPhLevel() {
        return phLevel;
    }

    public void setPhLevel(double phLevel) {
        this.phLevel = phLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(String waterQuality) {
        this.waterQuality = waterQuality;
    }

    public String getWildlifePresent() {
        return wildlifePresent;
    }

    public void setWildlifePresent(String wildlifePresent) {
        this.wildlifePresent = wildlifePresent;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", observerName='" + observerName + '\'' +
                ", observeDate='" + observeDate + '\'' +
                ", phLevel=" + phLevel +
                ", temperature=" + temperature +
                ", waterQuality='" + waterQuality + '\'' +
                ", wildlifePresent='" + wildlifePresent + '\'' +
                '}';
    }

}
