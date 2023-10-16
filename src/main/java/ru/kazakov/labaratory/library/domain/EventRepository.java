package ru.kazakov.labaratory.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Event;


import java.util.UUID;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {
}
