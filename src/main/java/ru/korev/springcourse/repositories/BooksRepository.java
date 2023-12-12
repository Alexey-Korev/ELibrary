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
    /*@Query("SELECT b.id, b.title, b.author, b.year FROM Person p JOIN Book b ON p.id = b.person.id WHERE p.id = :id")
    List<Book> findBooksByPerson(@Param("id") int id);*/
    List<Book> findBooksByPerson(Person person);
    @Modifying
    @Query("UPDATE Book SET person.id = null WHERE id=:id")
    void freeBook(int id);
}
