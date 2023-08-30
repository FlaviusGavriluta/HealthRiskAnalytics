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
        // Conversion from String to Date:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate birthDate = LocalDate.parse(person.birthDate(), formatter);

        // Current Date:
        LocalDate today = LocalDate.now();

        // Person's age:
        long age = ChronoUnit.YEARS.between(birthDate, today);

        return (int) age;
    }

    public double[] calculateBmiSeries(Person person) {
        double[] BMISeries = new double[person.weights().length];

        for (int i = 0; i < person.weights().length; i++) {
            BMISeries[i] = calculateBmi(person.height(), person.weights()[i]);
        }

        return BMISeries;
    }

    private static double calculateBmi(double height, int weight) {
        return weight / Math.pow(height, 2);
    }

    public WeightCondition determineWeightCondition(Person person) {
        double[] BMISeries = calculateBmiSeries(person);

        if (BMISeries.length < 3)
            return WeightCondition.Unknown;

        for (int i = 0; i < 3; i++)
            if (BMISeries[i] <= 25)
                return WeightCondition.Healthy;

        return WeightCondition.Overweight;
    }

    public double calculateOrr(Person[] persons) {
        return 0;
    }
}
