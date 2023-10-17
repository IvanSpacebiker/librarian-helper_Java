package ru.kazakov.labaratory.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.labaratory.library.entity.Event;
import ru.kazakov.labaratory.library.entity.EventType;
import ru.kazakov.labaratory.library.service.EventService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventService service;

    @GetMapping
    public Iterable<Event> getAllEvents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable UUID id)
    {
        return service.getById(id);
    }
    @PostMapping
    public Event addEvent(@RequestParam UUID bookid,
                          @RequestParam UUID readerid,
                          @RequestParam EventType type)
    {
        return service.add(bookid, readerid, type);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable UUID id)
    {
        service.delete(id);
    }
    
}
