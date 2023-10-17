package ru.kazakov.labaratory.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Book;
import ru.kazakov.labaratory.library.entity.Event;


import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {

//    Optional<Book> findTopByBookidAndTimeIsGreaterThan(Timestamp from, Timestamp to);


}
