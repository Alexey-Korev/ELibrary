package ru.korev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.korev.springcourse.RowMapper.BookRowMapper;
import ru.korev.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BookRowMapper());
    }
    public Book show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE id=?",
                new BookRowMapper(), id);
    }
    public List<Book> getBooksOfPerson(int id) {
        String sql = "SELECT b.id, b.title, b.author, b.year FROM person p JOIN book b ON p.id = b.person_id WHERE p.id =?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BookRowMapper());
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?,?,?)",
        book.getTitle(), book.getAuthor(), book.getYear());
    }
    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET person_id=?, title=?, author=?, year=? WHERE id=?",
                updateBook.getPerson_id(), updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }
    public void freeBook(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE id=?",
                id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

}

