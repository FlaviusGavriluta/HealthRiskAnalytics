package com.codecool.healthriskanalytics.service;

import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.model.WeightCondition;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyticsService {
    private static final double OVERWEIGHT_BMI_LIMIT = 25.0;
    private static final int OVERWEIGHT_YEARS_LIMIT = 3;

    public int calculateAge(Person person) {
        // Conversion from String to Date:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate birthDate = LocalDate.parse(person.birthDate(), formatter);

        LocalDate today = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthDate, today);

        return (int) age;
    }

    public double[] calculateBmiSeries(Person person) {
        List<Double> BMISeries = Arrays.stream(person.weights())
                .mapToDouble(weight -> calculateBmi(person.height(), weight))
                .boxed()
                .collect(Collectors.toList());

        return BMISeries.stream().mapToDouble(Double::doubleValue).toArray();
    }

    private static double calculateBmi(double height, int weight) {
        return weight / Math.pow(height, 2);
    }

    public WeightCondition determineWeightCondition(Person person) {
        double[] BMISeries = calculateBmiSeries(person);

        if (BMISeries.length < 3)
            return WeightCondition.Unknown;

        boolean isHealthy = Arrays.stream(BMISeries)
                .limit(3)
                .anyMatch(bmi -> bmi <= 25);

        return isHealthy ? WeightCondition.Healthy
                : WeightCondition.Overweight;
    }

    public double calculateOrr(Person[] persons) {
        Stream personsWithOverweightRisk = Arrays.stream(persons).filter(person -> determineWeightCondition(person).equals(WeightCondition.Overweight));

        return (double) personsWithOverweightRisk.count() / persons.length;
    }
}