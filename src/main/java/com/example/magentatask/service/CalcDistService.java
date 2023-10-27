package com.example.magentatask.service;

import com.example.magentatask.entity.City;
import com.example.magentatask.payload.CalcDistResponse;

import java.util.List;

public interface CalcDistService {

    List<CalcDistResponse> crowflightCalc(List<City> fromCities, List<City> toCities);
    List<CalcDistResponse> DistanceMatrixCalc(List<City> fromCities, List<City> toCities);
    List<CalcDistResponse> allCalc(List<City> fromCities, List<City> toCities);
}
