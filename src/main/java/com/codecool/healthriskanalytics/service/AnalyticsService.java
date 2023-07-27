package com.codecool.healthriskanalytics.service;

import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.model.WeightCondition;

public class AnalyticsService {
    private static final double OVERWEIGHT_BMI_LIMIT = 25.0;
    private static final int OVERWEIGHT_YEARS_LIMIT = 3;

    public int calculateAge(Person person) {
        return 0;
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
