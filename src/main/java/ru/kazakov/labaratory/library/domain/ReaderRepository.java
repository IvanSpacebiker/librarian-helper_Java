package ru.kazakov.labaratory.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Book;
import ru.kazakov.labaratory.library.entity.Reader;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, UUID> {

    Iterable<Reader> findAllByNameContainingAndSurnameContainingAllIgnoreCase(String name, String surname);

//    Optional<Book> findTopReader(Timestamp from, Timestamp to);
}
