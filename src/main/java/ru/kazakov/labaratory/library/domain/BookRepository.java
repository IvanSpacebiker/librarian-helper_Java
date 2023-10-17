package ru.kazakov.labaratory.library.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Book;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

    Iterable<Book> findAllByTitleContainingAndAuthorContainingAllIgnoreCase(String title, String author);

//    @Query("SELECT b from Book b INNER JOIN Event e ON b.id = e.bookid " +
//            "WHERE e.time >= :from AND e.time <= :to " +
//            "group by b.id order by count(b.id) desc")
//    Optional<Book> findTopBook(Timestamp from, Timestamp to);
}
