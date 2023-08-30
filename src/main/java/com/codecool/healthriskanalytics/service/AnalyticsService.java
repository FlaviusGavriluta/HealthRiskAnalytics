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
        int[] weights = person.weights();
        double[] bmiSeries = new double[weights.length];

        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            double bmi = weight / Math.pow(person.height(), 2);
            bmiSeries[i] = bmi;
        }

        return bmiSeries;
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
