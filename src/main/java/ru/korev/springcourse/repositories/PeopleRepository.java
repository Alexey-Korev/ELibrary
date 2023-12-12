package ru.korev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.korev.springcourse.models.Book;
import ru.korev.springcourse.models.Person;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository <Person, Integer> {

    Optional<Person> findByName(String title);

    /*@Query("select p.id, p.name, p.year from Person p join Book b on p.id = b.person.id where b.id=:id")
    Optional<Person> findPersonByBook(@Param("id") Integer id);*/
    Optional<Person> findPersonByBooksContaining(Book book);
}
