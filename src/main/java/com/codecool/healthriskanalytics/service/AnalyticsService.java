package com.codecool.healthriskanalytics.service;

import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.model.WeightCondition;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AnalyticsService {
    private static final double OVERWEIGHT_BMI_LIMIT = 25.0;
    private static final int OVERWEIGHT_YEARS_LIMIT = 3;

    public int calculateAge(Person person) {
        // Parse the birth date string to a LocalDate object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(person.birthDate() , formatter);

        // Get the current date
        LocalDate today = LocalDate.now();

        // Calculate the age
        long age = ChronoUnit.YEARS.between(birthDate, today);

        return (int) age;
    }

    public double[] calculateBmiSeries(Person person) {
        return new double[0];
    }

    private static double calculateBmi(double height, int weight) {
        return 0;
    }

    public WeightCondition determineWeightCondition(Person person) {
        return WeightCondition.Healthy;
    }

    public double calculateOrr(Person[] persons) {
        return 0;
    }
}
