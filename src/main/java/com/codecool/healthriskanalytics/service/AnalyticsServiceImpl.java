package com.codecool.healthriskanalytics.service;

import com.codecool.healthriskanalytics.model.AgeGroup;
import com.codecool.healthriskanalytics.model.Gender;
import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.model.WeightCondition;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyticsServiceImpl implements AnalyticsService {
    private static final double OVERWEIGHT_BMI_LIMIT = 25.0;
    private static final int OVERWEIGHT_YEARS_LIMIT = 3;

    @Override
    public int calculateAge(Person person) {
        // Conversion from String to Date:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate birthDate = LocalDate.parse(person.birthDate(), formatter);

        LocalDate today = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthDate, today);

        return (int) age;
    }

    @Override
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

    @Override
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

    @Override
    public double calculateOrr(Person[] persons) {
        Stream personsWithOverweightRisk = Arrays.stream(persons)
                .filter(person -> determineWeightCondition(person).equals(WeightCondition.Overweight));

        return (double) personsWithOverweightRisk.count() / persons.length;
    }

    @Override
    public double calculateOrr(Person[] persons, Gender gender) {
        List<Person> personsByGender = Arrays.stream(persons)
                .filter(person -> person.gender().equals(gender)).collect(Collectors.toList());

        if (personsByGender.isEmpty())
            return 0;

        return calculateOrr(personsByGender.toArray(new Person[0]));
    }

    @Override
    public double calculateOrr(Person[] persons, AgeGroup ageGroup) {
        List<Person> personsInAgeGroup = Arrays.stream(persons)
                .filter(person -> {
                    int age = calculateAge(person);
                    switch (ageGroup) {
                        case YOUNG_ADULT:
                            return age >= 18 && age <= 25;
                        case ADULT:
                            return age >= 26 && age <= 44;
                        case MIDDLE_AGE:
                            return age >= 45 && age <= 59;
                        case OLD:
                            return age >= 60;
                        default:
                            return false;
                    }
                }).collect(Collectors.toList());

        if (personsInAgeGroup.isEmpty())
            return 0;

        return calculateOrr(personsInAgeGroup.toArray(new Person[0]));
    }
}