package ru.korev.springcourse.controllers;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.korev.springcourse.models.Book;
import ru.korev.springcourse.models.Person;
import ru.korev.springcourse.services.BooksService;
import ru.korev.springcourse.services.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(@NotNull Model model) {
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,
                           @ModelAttribute("person") Person person) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        if (book.getPerson() != null) {
            model.addAttribute("person", book.getPerson());
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }
    /*@GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, @ModelAttribute("person") Person person,
                       Model model) {
        List<Person> people = peopleService.findAll();
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("reader", peopleService.findOneByBook(id));
        model.addAttribute("people", people);
        return "books/show";
    }*/


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors() )
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/person")
    public String giveBookToPerson(@RequestParam("personId") int personId, @PathVariable("id") int bookId) {
        Person person = peopleService.findOne(personId);
        Book book = booksService.findOne(bookId);
        book.setPerson(person);
        booksService.update(bookId, book);
        return "redirect:/books/{id}";
    }
    /*@PatchMapping("/{id}/person")
    public String giveBookToPerson(@ModelAttribute("person") Person person,
                                   @PathVariable("id") int id) {
        Book book = booksService.findOne(id);
        book.setPerson(person);
        booksService.update(id, book);
        return "redirect:/books/{id}";
    }*/
    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        Book book = booksService.findOne(bookId);
        book.setPerson(null);
        booksService.update(bookId, book);
        return "redirect:/books/{id}";
    }
    /*@PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int id) {
        booksService.freeBook(id);
        return "redirect:/books/{id}";
    }*/
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}