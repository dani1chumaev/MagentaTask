package com.example.magentatask.repository;

import com.example.magentatask.entity.City;
import com.example.magentatask.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

    Optional<Distance> getDistanceByFromCityAndToCity(City fromCity, City toCity);
}
