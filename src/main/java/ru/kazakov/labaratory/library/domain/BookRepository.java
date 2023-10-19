package ru.kazakov.labaratory.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Book;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    List<Book> findAllByTitleContainingAndAuthorContainingAllIgnoreCase(String title, String author);
    Book findBookByTitleAndAuthorAllIgnoreCase(String title, String author);
}
