package com.example.magentatask.service.Impl;

import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Distance;
import com.example.magentatask.exception.DistanceNotFoundException;
import com.example.magentatask.repository.DistanceRepository;
import com.example.magentatask.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistanceServiceImpl implements DistanceService {

    @Autowired
    DistanceRepository distanceRepository;

    @Override
    public Distance getDistanceByFromCityAndToCity(City fromCity, City toCity) {
        return distanceRepository.getDistanceByFromCityAndToCity(fromCity, toCity)
                .orElseThrow(() -> new DistanceNotFoundException(String.format("Distance from %s to %s not found", fromCity.getName(), toCity.getName())));
    }

    @Override
    public List<Distance> createDistances(List<Distance> distances) {
        return distanceRepository.saveAll(distances);
    }
}
