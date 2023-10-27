package ru.korev.springcourse.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public class Book {
    private int id;
    private int person_id;
    @NotEmpty(message = "Enter name of the book, please")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 chracters")
    private String name;
    @NotEmpty(message = "Enter authoe name, please")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 chracters")
    private String author;
    private int year;
    public Book() {

    }
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }
    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
