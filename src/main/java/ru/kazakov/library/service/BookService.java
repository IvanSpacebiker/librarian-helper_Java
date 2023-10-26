package ru.kazakov.library.service;

import ru.kazakov.library.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService extends EntityService<Book>{

    List<Book> getBookByTitleAndAuthor(String title, String author);
    Book addBook(String title, String author, int quantity);
    Book editBook(UUID id, String title, String author, int quantity);

}
