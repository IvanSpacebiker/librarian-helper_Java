package ru.kazakov.library.service;

import ru.kazakov.library.entity.Event;
import ru.kazakov.library.entity.EventType;

import java.util.List;
import java.util.UUID;

public interface EventService {

    List<Event> getAllEvents();
    Event getEventById(UUID id);
    Event addEvent(UUID bookid, UUID readerid, EventType type);
    void deleteEvent(UUID id);

}
