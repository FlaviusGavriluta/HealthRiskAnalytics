package com.codecool.healthriskanalytics;

import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.service.AnalyticsService;
import com.codecool.healthriskanalytics.service.AnalyticsServiceImpl;
import com.codecool.healthriskanalytics.service.PersonProvider;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        PersonProvider personProvider = new PersonProvider(20);
        Person[] persons = personProvider.getPersons();

        AnalyticsService analyticsService = new AnalyticsServiceImpl();

        // Testează calculateBmiSeries pentru prima persoană
        double[] bmiSeries = analyticsService.calculateBmiSeries(persons[1]);
        System.out.println("BMI for the person number " + persons[1].id() + ": " + Arrays.toString(bmiSeries));
        System.out.print("Person number " + persons[1].id() + " is " + analyticsService.calculateAge(persons[1]) + " years old ");
        System.out.println("and is " + analyticsService.determineWeightCondition(persons[1]));
        System.out.println("_______________________________________________________________");

        System.out.println("Overweight risk ratio: " + analyticsService.calculateOrr(persons));
    }
}
