package ru.korev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.korev.springcourse.models.Book;
import ru.korev.springcourse.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    List<Book> findByTitleStartingWithIgnoreCase (String startString);
    List<Book> findBooksByReader(Person reader);
}
