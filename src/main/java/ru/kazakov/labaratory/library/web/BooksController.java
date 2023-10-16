package ru.kazakov.labaratory.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kazakov.labaratory.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService service;

    @GetMapping
    public void getAllBooks() {
        service.addBook("bad", "dab", 1);
    }


}
