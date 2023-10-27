package com.example.magentatask.service.Impl;

import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Dto.CityDto;
import com.example.magentatask.exception.CityNotFoundException;
import com.example.magentatask.repository.CityRepository;
import com.example.magentatask.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.*;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.getCityByName(name)
                .orElseThrow(() -> new CityNotFoundException(String.format("City name %s not found", name)));
    }

    @Override
    public List<City> createCities(List<City> cities) {
            return cityRepository.saveAll(cities);
    }

    //Distance in km
    @Override
    public Double calcCrowflightDistance(City fromCity, City toCity) {
        double R = 6373.0;

        double lat1 = toRadians(fromCity.getLatitude());
        double lon1 = toRadians(fromCity.getLongitude());
        double lat2 = toRadians(toCity.getLatitude());
        double lon2 = toRadians(toCity.getLongitude());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;


        double a = pow(sin(dlat / 2), 2) + cos(lat1) * cos(lat2) * pow(sin(dlon / 2), 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return R * c;
    }

    @Override
    public CityDto cityToDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        return cityDto;
    }
}
