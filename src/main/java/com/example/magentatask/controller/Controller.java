package com.example.magentatask.controller;

import com.example.magentatask.entity.CalculationType;
import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Distance;
import com.example.magentatask.payload.CalcDistResponse;
import com.example.magentatask.payload.CityDistanceXml;
import com.example.magentatask.service.CalcDistService;
import com.example.magentatask.service.CityService;
import com.example.magentatask.service.DistanceService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    CityService cityService;
    @Autowired
    DistanceService distanceService;
    @Autowired
    CalcDistService calcDistService;

    @GetMapping(value = "/cities/get")
    public ResponseEntity<?> getCities() {

        return ResponseEntity.ok(cityService.getAllCities().stream()
                .map(city -> cityService.cityToDto(city))
                .toList());
    }

    //Calculate distance for all possible combinations
    @GetMapping(value = "/distances/calc", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> calcDist(@RequestParam("calculationType") CalculationType calculationType,
                                      @RequestParam("fromCity") List<String> fromCity,
                                      @RequestParam("toCity") List<String> toCity
    ) {

        List<City> retrievedFromCities = fromCity.stream()
                .map(name -> cityService.getCityByName(name))
                .toList();
        List<City> retrievedToCities = toCity.stream()
                .map(name -> cityService.getCityByName(name))
                .toList();

        List<CalcDistResponse> calcDistResponse = switch (calculationType) {
            case Crowflight -> calcDistService.crowflightCalc(retrievedFromCities, retrievedToCities);
            case DistanceMatrix -> calcDistService.DistanceMatrixCalc(retrievedFromCities, retrievedToCities);
            case All -> calcDistService.allCalc(retrievedFromCities, retrievedToCities);
        };

        return ResponseEntity.ok(calcDistResponse);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadXMl(@RequestParam("file") MultipartFile file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        CityDistanceXml cityDistance = xmlMapper.readValue(file.getInputStream(), CityDistanceXml.class);

        cityService.createCities(cityDistance.getCities());

        List<Distance> distances = cityDistance.getDistances().stream().map(distance -> {
            Distance newDist = new Distance();
            newDist.setFromCity(cityService.getCityByName(distance.getFromCity().getName()));
            newDist.setToCity(cityService.getCityByName(distance.getToCity().getName()));
            newDist.setDistance(distance.getDistance());
            return newDist;
        }).toList();

        distanceService.createDistances(distances);

        return ResponseEntity.ok().build();
    }
}
