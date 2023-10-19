package ru.kazakov.labaratory.library.service;

import ru.kazakov.labaratory.library.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService extends EntityService<Book>{

    List<Book> getByTitleAndAuthor(String title, String author);
    Book add(String title, String author, int quantity);
    Book edit(UUID id, String title, String author, int quantity);

}
