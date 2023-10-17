package ru.kazakov.labaratory.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.BookRepository;
import ru.kazakov.labaratory.library.entity.Book;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Iterable<Book> getByTitleAndAuthor(String title, String author) {
        return bookRepository.findAllByTitleContainingAndAuthorContainingAllIgnoreCase(title, author);
    }

    public Optional<Book> getById(UUID id) {
        return bookRepository.findById(id);
    }

    public Book add(String title, String author, int quantity) {
        return bookRepository.save(Book.builder()
                        .id(UUID.randomUUID())
                        .title(title)
                        .author(author)
                        .quantity(quantity)
                        .build());
    }

    public Book edit(UUID id, String title, String author, int quantity) {
        if (bookRepository.existsById(id)) {
            return bookRepository.save(Book.builder()
                            .id(id)
                            .title(title)
                            .author(author)
                            .quantity(quantity)
                            .build());
        }
        return null;
    }

    public void delete(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }

//    public Optional<Book> getTop(Timestamp from, Timestamp to) {
////        return bookRepository.findTopBook(from, to);
//    }


}
