package ru.kazakov.labaratory.library.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.*;
import ru.kazakov.labaratory.library.entity.*;
import ru.kazakov.labaratory.library.exceptions.*;
import ru.kazakov.labaratory.library.service.EventService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Override
    public List<Event> getAll() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public Event getById(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event not found");
        }
        return eventRepository.findById(id).get();
    }

    @Override
    public Event add(UUID bookid, UUID readerid, EventType type) throws NotFoundException, OutOfStockException {

        if (!bookRepository.existsById(bookid) || bookRepository.findById(bookid).isEmpty()) {
            throw new NotFoundException("Book not found");
        }
        if (!readerRepository.existsById(readerid)) {
            throw new NotFoundException("Reader not found");
        }

        Book book = bookRepository.findById(bookid).get();
        if (book.getQuantity() == 0 && type == EventType.TAKE) throw new OutOfStockException("No book copies remained");
        book.setQuantity(type == EventType.TAKE ? book.getQuantity() - 1 : book.getQuantity() + 1);

        return eventRepository.save(Event.builder()
                .id(UUID.randomUUID())
                .bookid(bookid)
                .readerid(readerid)
                .type(type)
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .build());
    }

    @Override
    public void delete(UUID id) throws NotFoundException {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException("Event not found");
        }
        eventRepository.deleteById(id);
    }
    
}
