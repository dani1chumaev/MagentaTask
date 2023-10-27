package com.example.magentatask.service;

import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Dto.CityDto;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City getCityByName(String name);

    List<City> createCities(List<City> cities);

    Double calcCrowflightDistance(City fromCity, City toCity);

    CityDto cityToDto(City city);
}
