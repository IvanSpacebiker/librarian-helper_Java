package ru.kazakov.labaratory.library.service;

import ru.kazakov.labaratory.library.entity.Event;
import ru.kazakov.labaratory.library.entity.EventType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {

    List<Event> getAll();
    Event getById(UUID id);
    Event add(UUID bookid, UUID readerid, EventType type);
    void delete(UUID id);

}
