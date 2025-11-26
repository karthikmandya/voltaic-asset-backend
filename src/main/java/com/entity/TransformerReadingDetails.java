package com.entity;

import java.time.LocalDateTime;

public class TransformerReadingDetails {

    int id;
    String name;
    LocalDateTime date;
    double windingTemperature;
    double topOilTemperature;
    double humidity;
    double l1;
    double l2;
    double l3;




    public TransformerReadingDetails(String name, LocalDateTime date, double windingTemperature, double topOilTemperature, double humidity) {
        this.name = name;
        this.date = date;
        this.windingTemperature = windingTemperature;
        this.topOilTemperature = topOilTemperature;
        this.humidity = humidity;
        this.l1 = 0;
        this.l2 = 0;
        this.l3 = 0;
    }

    public TransformerReadingDetails(int id, String name, LocalDateTime date, double windingTemperature, double topOilTemperature, double humidity, double l1, double l2, double l3) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.windingTemperature = windingTemperature;
        this.topOilTemperature = topOilTemperature;
        this.humidity = humidity;
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
    }

    public TransformerReadingDetails() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setWindingTemperature(double windingTemperature) {
        this.windingTemperature = windingTemperature;
    }

    public void setTopOilTemperature(double topOilTemperature) {
        this.topOilTemperature = topOilTemperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setL1(double l1) {
        this.l1 = l1;
    }

    public void setL2(double l2) {
        this.l2 = l2;
    }

    public void setL3(double l3) {
        this.l3 = l3;
    }

    public int getId() {
        return id;
    }

    public double getL1() {
        return l1;
    }

    public double getL2() {
        return l2;
    }

    public double getL3() {
        return l3;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getWindingTemperature() {
        return windingTemperature;
    }

    public double getTopOilTemperature() {
        return topOilTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "TransformerReadingDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", windingTemperature=" + windingTemperature +
                ", topOilTemperature=" + topOilTemperature +
                ", humidity=" + humidity +
                '}';
    }
}
