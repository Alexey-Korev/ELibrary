package ru.korev.springcourse.controllers;

import jakarta.validation.Valid;
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
    public String index(@RequestParam(value = "page", required = false, defaultValue = "-1") int page,
                        @RequestParam(value = "books_per_page", required = false, defaultValue = "-1") int booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false, defaultValue = "false") boolean sortByYear,
                        Model model) {
        List<Book> books;
        if (page != -1 && booksPerPage != -1) {
            books = sortByYear
                    ? booksService.findAndPageAndSortByYear(page, booksPerPage)
                    : booksService.findAndPage(page, booksPerPage);
        } else if (page == -1 && booksPerPage == -1 && sortByYear) {
            books = booksService.findAndSortByYear();
        } else {
            books = booksService.findAll();
        }
        model.addAttribute("books", books);
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person,
                       Model model) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        if (book.getReader() != null) {
            model.addAttribute("reader", book.getReader());
            System.out.println(book.getReader());
        } else {
            model.addAttribute("people", peopleService.findAll());
            System.out.println("people");
        }
        return "books/show";
    }
    //через дебагер там возвращается айди6, посмотри в дебагере
    /*@GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, @ModelAttribute("person") Person person,
                       Model model) {
        List<Person> people = peopleService.findAll();
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("reader", booksService.findBooksByPerson(person));
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
    public String giveBookToPerson(@ModelAttribute("person") Person person,
                                   @PathVariable("id") int id) {
        booksService.addBookToPerson(person, id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        booksService.freeBook(bookId);
        return "redirect:/books/{id}";
    }
    @GetMapping("/search")
    public String searchBook(@RequestParam(required = false, defaultValue = "") String startString,
                             Model model) {
        model.addAttribute("startString", startString);
        if (!startString.isEmpty()) {
            model.addAttribute("books", booksService.searchBooks(startString));
        }
        return "books/search";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}