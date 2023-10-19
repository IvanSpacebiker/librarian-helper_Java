package ru.kazakov.labaratory.library.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.labaratory.library.dto.EventDTO;
import ru.kazakov.labaratory.library.dto.mapper.EventDTOMapper;
import ru.kazakov.labaratory.library.entity.EventType;
import ru.kazakov.labaratory.library.service.implementation.EventServiceImpl;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/events")
public class EventsController {

    private EventServiceImpl service;
    private EventDTOMapper eventDTOMapper;

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents()
    {
        return ResponseEntity.ok(
                service.getAll().stream().map(eventDTOMapper::apply).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(
                eventDTOMapper.apply(service.getById(id))
        );
    }
    @PostMapping
    public ResponseEntity<EventDTO> addEvent(@RequestParam UUID bookid,
                          @RequestParam UUID readerid,
                          @RequestParam EventType type)
    {
        return ResponseEntity.ok(
                eventDTOMapper.apply(service.add(bookid, readerid, type))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable UUID id) {
        service.delete(id);
    }
    
}
