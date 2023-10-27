package com.example.magentatask.payload;

import com.example.magentatask.entity.CalculationType;

public class CalcDistResponse {
    private CalculationType calculationType;
    private String fromCity;
    private String toCity;
    private Double distance;
    private String unit;

    public CalcDistResponse() {
    }

    public CalcDistResponse(CalculationType calculationType, String fromCity, String toCity, Double distance, String unit) {
        this.calculationType = calculationType;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
        this.unit = unit;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(CalculationType calculationType) {
        this.calculationType = calculationType;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
