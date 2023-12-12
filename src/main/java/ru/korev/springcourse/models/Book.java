package ru.korev.springcourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private int person_id;
    @NotEmpty(message = "Type title")
    @Size(min = 2, max = 50, message = "between 2 and 50")
    private String title;
    @NotEmpty(message = "Type author name")
    @Size(min = 2, max = 50, message = "between 2 and 50")
    private String author;
    @Max(value = 2023, message = "less than 2023")
    @Min(value = 1500, message = "more than 1500")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {

    }
    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getPerson_id() {
//        return person_id;
//    }
//
//    public void setPerson_id(int person_id) {
//        this.person_id = person_id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
