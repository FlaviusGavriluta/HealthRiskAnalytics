package com.codecool.healthriskanalytics.model;

import com.codecool.healthriskanalytics.model.Gender;

public record Person(int id, String birthDate, Gender gender, double height, int[] weights) {
}
