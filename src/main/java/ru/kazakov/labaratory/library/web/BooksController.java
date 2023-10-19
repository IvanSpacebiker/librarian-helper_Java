package ru.kazakov.labaratory.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.labaratory.library.entity.Book;
import ru.kazakov.labaratory.library.service.BookService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService service;

    @GetMapping
    public Iterable<Book> getBookByTitleAndAuthor(@RequestParam(defaultValue = "") String title,
                                                  @RequestParam(defaultValue = "") String author)
    {
        return service.getByTitleAndAuthor(title, author);
    }
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @GetMapping("/top")
    public Optional<Book> getTopBook(@RequestParam(defaultValue = "") String from,
                                     @RequestParam(defaultValue = "") String to) {
        return service.getTop(from, to);
    }

    @PostMapping
    public Book addBook(@RequestParam String title,
                        @RequestParam String author,
                        @RequestParam(defaultValue = "1") int quantity)
    {
        return service.add(title, author, quantity);
    }



    @PutMapping("/{id}")
    public Book editBook(@PathVariable UUID id,
                         @RequestParam String title,
                         @RequestParam String author,
                         @RequestParam int quantity)
    {
        return service.edit(id, title, author, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id)
    {
        service.delete(id);
    }

}
