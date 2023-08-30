package com.codecool.healthriskanalytics.ui;

import com.codecool.healthriskanalytics.model.AgeGroup;
import com.codecool.healthriskanalytics.model.Gender;
import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.model.WeightCondition;
import com.codecool.healthriskanalytics.service.AnalyticsService;

import java.util.Arrays;

public class HealthRiskUi {
    private final AnalyticsService analyticsService;

    public HealthRiskUi(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    public void displayIndividualAnalysis(Person[] persons) {
        for (Person person : persons) {
            int age = analyticsService.calculateAge(person);
            double[] BMISeries = analyticsService.calculateBmiSeries(person);
            WeightCondition condition = analyticsService.determineWeightCondition(person);

            System.out.println("-------------------------------------------------------------");
            System.out.println("Name: " + person.id());
            System.out.println("Age: " + age);
            System.out.println("Gender: " + person.gender());
            System.out.println("BMI series: " + Arrays.toString(BMISeries));
            System.out.println("Weight condition: " + condition);
        }
    }

    public void displayStatistics(Person[] persons) {
        double calculateORR = analyticsService.calculateOrr(persons);
        double calculateORRForMale = analyticsService.calculateOrr(persons, Gender.MALE);
        double calculateORRForFemale = analyticsService.calculateOrr(persons, Gender.FEMALE);
        double calculateORRForYoung = analyticsService.calculateOrr(persons, AgeGroup.YOUNG_ADULT);
        double calculateORRForAdult = analyticsService.calculateOrr(persons, AgeGroup.ADULT);
        double calculateORRForMiddle = analyticsService.calculateOrr(persons, AgeGroup.MIDDLE_AGE);
        double calculateORRForOld = analyticsService.calculateOrr(persons, AgeGroup.OLD);

        System.out.println("-----------------------------------------------------------------");
        System.out.println("General Statistics");
        System.out.println("TOTAL overweight risk ratio: " + calculateORR);
        System.out.println("ORR for male: " + calculateORRForMale);
        System.out.println("ORR for female: " + calculateORRForFemale);
        System.out.println("ORR for young: " + calculateORRForYoung);
        System.out.println("ORR for adult: " + calculateORRForAdult);
        System.out.println("ORR for middle age: " + calculateORRForMiddle);
        System.out.println("ORR for old: " + calculateORRForOld);
    }
}