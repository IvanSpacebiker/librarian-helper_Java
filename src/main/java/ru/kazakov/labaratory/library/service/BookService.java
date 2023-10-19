package ru.kazakov.labaratory.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.BookRepository;
import ru.kazakov.labaratory.library.domain.EventRepository;
import ru.kazakov.labaratory.library.entity.Book;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private EventRepository eventRepository;
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

    public Optional<Book> getTop(String from, String to) {
        Timestamp f = Objects.equals(from, "") ? Timestamp.valueOf("1970-01-01 00:00:00") : Timestamp.valueOf(LocalDateTime.parse(from));
        Timestamp t = Objects.equals(to, "") ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(LocalDateTime.parse(to));
        return bookRepository.findById(eventRepository.findTopBook(f, t).iterator().next());
    }
}
