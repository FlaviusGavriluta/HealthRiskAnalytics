package com.codecool.healthriskanalytics.service;

import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.model.WeightCondition;

public interface AnalyticsService {
    int calculateAge(Person person);

    double[] calculateBmiSeries(Person person);

    WeightCondition determineWeightCondition(Person person);

    double calculateOrr(Person[] persons);
}
