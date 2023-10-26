package ru.kazakov.library.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.library.dto.EventDTO;
import ru.kazakov.library.dto.mapper.EventDTOMapper;
import ru.kazakov.library.entity.EventType;
import ru.kazakov.library.service.implementation.EventServiceImpl;

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
                service.getAllEvents()
                        .stream()
                        .map(eventDTOMapper::apply)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(
                eventDTOMapper.apply(service.getEventById(id))
        );
    }
    @PostMapping
    public ResponseEntity<EventDTO> addEvent(@RequestParam UUID bookid,
                          @RequestParam UUID readerid,
                          @RequestParam EventType type)
    {
        return ResponseEntity.ok(
                eventDTOMapper.apply(service.addEvent(bookid, readerid, type))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable UUID id) {
        service.deleteEvent(id);
    }
    
}
