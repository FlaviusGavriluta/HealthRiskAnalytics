package com.codecool.healthriskanalytics;

import com.codecool.healthriskanalytics.model.Person;
import com.codecool.healthriskanalytics.service.AnalyticsService;
import com.codecool.healthriskanalytics.service.AnalyticsServiceImpl;
import com.codecool.healthriskanalytics.service.PersonProvider;

public class Application {

    public static void main(String[] args) {
        PersonProvider personProvider = new PersonProvider(20);
        Person[] persons = personProvider.getPersons();

        AnalyticsService analyticsService = new AnalyticsServiceImpl();

    }
}
