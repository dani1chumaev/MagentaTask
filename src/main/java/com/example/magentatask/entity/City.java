package com.example.magentatask.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @OneToMany(mappedBy = "fromCity")
    private List<Distance> distancesFromCity;
    @OneToMany(mappedBy = "toCity")
    private List<Distance> distancesToCity;

    public City() {
    }

    public City(Long id, String name, Double latitude, Double longitude, List<Distance> distancesFromCity, List<Distance> distancesToCity) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distancesFromCity = distancesFromCity;
        this.distancesToCity = distancesToCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Distance> getDistancesFromCity() {
        return distancesFromCity;
    }

    public void setDistancesFromCity(List<Distance> distancesFromCity) {
        this.distancesFromCity = distancesFromCity;
    }

    public List<Distance> getDistancesToCity() {
        return distancesToCity;
    }

    public void setDistancesToCity(List<Distance> distancesToCity) {
        this.distancesToCity = distancesToCity;
    }
}
