package ru.kazakov.labaratory.library.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.BookRepository;
import ru.kazakov.labaratory.library.domain.EventRepository;
import ru.kazakov.labaratory.library.entity.Book;
import ru.kazakov.labaratory.library.exceptions.AlreadyExistsException;
import ru.kazakov.labaratory.library.exceptions.NotFoundException;
import ru.kazakov.labaratory.library.service.BookService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final EventRepository eventRepository;

    @Override
    public List<Book> getByTitleAndAuthor(String title, String author) {
        return bookRepository.findAllByTitleContainingAndAuthorContainingAllIgnoreCase(title, author);
    }

    @Override
    public Book getById(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found");
        }
        return bookRepository.findById(id).get();
    }

    @Override
    public Book getTop(String from, String to) {
        Timestamp f = Objects.equals(from, "") ? Timestamp.valueOf("1970-01-01 00:00:00") : Timestamp.valueOf(LocalDateTime.parse(from));
        Timestamp t = Objects.equals(to, "") ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(LocalDateTime.parse(to));
        if (eventRepository.findTopBook(f, t).isEmpty()) throw new NotFoundException("Books not found");
        return bookRepository.findById(eventRepository.findTopBook(f, t).iterator().next()).get();
    }

    @Override
    public Book add(String title, String author, int quantity) throws AlreadyExistsException {

        if (bookRepository.findBookByTitleAndAuthorAllIgnoreCase(title, author) != null) {
            throw new AlreadyExistsException("Book already exists");
        }

        return bookRepository.save(Book.builder()
                        .id(UUID.randomUUID())
                        .title(title)
                        .author(author)
                        .quantity(quantity)
                        .build());
    }

    @Override
    public Book edit(UUID id, String title, String author, int quantity) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found");
        }

        Book book = bookRepository.findById(id).get();
        if (!Objects.equals(title, "")) book.setTitle(title);
        if (!Objects.equals(author, "")) book.setAuthor(author);
        if (quantity != -1) book.setQuantity(quantity);

        return bookRepository.save(book);

    }

    @Override
    public void delete(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

}
