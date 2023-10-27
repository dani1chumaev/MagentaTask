package com.example.magentatask.payload;

import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Distance;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "wrapper")
public class CityDistanceXml {
    @JacksonXmlElementWrapper(localName = "cities")
    @JacksonXmlProperty(localName = "city")
    private List<City> cities;
    @JacksonXmlElementWrapper(localName = "distances")
    @JacksonXmlProperty(localName = "distance")
    private List<Distance> distances;

    public CityDistanceXml() {
    }

    public CityDistanceXml(List<City> cities, List<Distance> distances) {
        this.cities = cities;
        this.distances = distances;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }
}
