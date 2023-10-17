package ru.kazakov.labaratory.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.EventRepository;
import ru.kazakov.labaratory.library.entity.Event;
import ru.kazakov.labaratory.library.entity.EventType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Iterable<Event> getAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> getById(UUID id) {
        return eventRepository.findById(id);
    }

    public Event add(UUID bookid, UUID readerid, EventType type) {
        return eventRepository.save(Event.builder()
                .id(UUID.randomUUID())
                .bookid(bookid)
                .readerid(readerid)
                .type(type)
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .build());
    }

    public void delete(UUID id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        }
    }
    
}
