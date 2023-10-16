package ru.kazakov.labaratory.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.BookRepository;
import ru.kazakov.labaratory.library.entity.Book;

import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(String title, String author, int quantity) {
        bookRepository.save(Book.builder()
                        .id(UUID.randomUUID())
                        .quantity(quantity)
                        .title(title)
                        .author(author)
                        .build());
    }

}
