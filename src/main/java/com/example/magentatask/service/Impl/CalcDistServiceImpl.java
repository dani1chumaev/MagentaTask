package com.example.magentatask.service.Impl;

import com.example.magentatask.entity.CalculationType;
import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Distance;
import com.example.magentatask.payload.CalcDistResponse;
import com.example.magentatask.service.CalcDistService;
import com.example.magentatask.service.CityService;
import com.example.magentatask.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcDistServiceImpl implements CalcDistService {

    @Autowired
    CityService cityService;
    @Autowired
    DistanceService distanceService;

    @Override
    public List<CalcDistResponse> crowflightCalc(List<City> fromCities, List<City> toCities) {

        List<CalcDistResponse> calcDistResponse = new ArrayList<>();

        fromCities.forEach(fromCity -> {
            toCities.forEach(toCity -> {

                Double crowflightDist = cityService.calcCrowflightDistance(fromCity, toCity);

                calcDistResponse.add(new CalcDistResponse(
                        CalculationType.Crowflight,
                        fromCity.getName(),
                        toCity.getName(),
                        crowflightDist,
                        "km"));
            });
        });

        return calcDistResponse;
    }

    @Override
    public List<CalcDistResponse> DistanceMatrixCalc(List<City> fromCities, List<City> toCities) {

        List<CalcDistResponse> calcDistResponse = new ArrayList<>();

        fromCities.forEach(fromCity -> {
            toCities.forEach(toCity -> {

                Distance distance = distanceService.getDistanceByFromCityAndToCity(fromCity, toCity);

                calcDistResponse.add(new CalcDistResponse(
                        CalculationType.DistanceMatrix,
                        fromCity.getName(),
                        toCity.getName(),
                        distance.getDistance(),
                        "km"));
            });
        });

        return calcDistResponse;
    }

    @Override
    public List<CalcDistResponse> allCalc(List<City> fromCities, List<City> toCities) {

        List<CalcDistResponse> calcDistResponse = new ArrayList<>();

        fromCities.forEach(fromCity -> {
            toCities.forEach(toCity -> {

                Distance distance = distanceService.getDistanceByFromCityAndToCity(fromCity, toCity);
                Double crowflightDist = cityService.calcCrowflightDistance(fromCity, toCity);

                calcDistResponse.add(new CalcDistResponse(
                        CalculationType.Crowflight,
                        fromCity.getName(),
                        toCity.getName(),
                        crowflightDist,
                        "km"));
                calcDistResponse.add(new CalcDistResponse(
                        CalculationType.DistanceMatrix,
                        fromCity.getName(),
                        toCity.getName(),
                        distance.getDistance(),
                        "km"));
            });
        });

        return calcDistResponse;
    }
}
