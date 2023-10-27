package ru.korev.springcourse.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Enter name please")
    @Size(min = 2, max = 30, message = "name should be between 2 and 30 characters")
    private String name;
    @Min(value = 1940, message = "birth year should be more than 1940")
    @Max(value = 2016, message = "birth year should be less than 2016")
    private int year;
    public Person() {

    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
