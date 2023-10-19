package ru.kazakov.labaratory.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Book;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    Iterable<Book> findAllByTitleContainingAndAuthorContainingAllIgnoreCase(String title, String author);

}
