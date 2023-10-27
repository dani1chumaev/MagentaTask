package com.example.magentatask.service;

import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Distance;

import java.util.List;

public interface DistanceService {

    Distance getDistanceByFromCityAndToCity(City fromCity, City toCity);

    List<Distance> createDistances(List<Distance> distances);
}
