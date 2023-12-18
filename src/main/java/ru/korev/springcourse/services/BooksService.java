package ru.korev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korev.springcourse.models.Book;
import ru.korev.springcourse.models.Person;
import ru.korev.springcourse.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }
    public Book findOne(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }
    public List<Book> findAndSortByYear() {
        return booksRepository.findAll(Sort.by("year"));
    }
    public List<Book> findAndPage(int page, int itemsOnPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsOnPage)).getContent();
    }
    public List<Book> findAndPageAndSortByYear(int page, int itemsOnPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsOnPage, Sort.by("year"))).getContent();
    }
    public List<Book> findBooksByPerson(Person reader) {
        return booksRepository.findBooksByReader(reader);
    }
    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }
    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    @Transactional
    public void addBookToPerson(Person reader, int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null) {
            book.setReader(reader);
            booksRepository.save(book);
        }
    }
    @Transactional
    public void freeBook(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null) {
            book.setReader(null);
            booksRepository.save(book);
        }
    }
    public List<Book> searchBooks(String startString) {
        return booksRepository.findByTitleStartingWithIgnoreCase(startString);
    }
    @Transactional
    public void delete (int id) {
        booksRepository.deleteById(id);
    }
}
