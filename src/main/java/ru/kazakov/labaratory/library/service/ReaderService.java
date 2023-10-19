package ru.kazakov.labaratory.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.EventRepository;
import ru.kazakov.labaratory.library.domain.ReaderRepository;
import ru.kazakov.labaratory.library.entity.Book;
import ru.kazakov.labaratory.library.entity.Reader;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private EventRepository eventRepository;

    public Iterable<Reader> getByNameAndSurname(String name, String surname) {
        return readerRepository.findAllByNameContainingAndSurnameContainingAllIgnoreCase(name, surname);
    }

    public Optional<Reader> getById(UUID id) {
        return readerRepository.findById(id);
    }

    public Reader add(String name, String surname) {
        return readerRepository.save(Reader.builder()
                .id(UUID.randomUUID())
                .name(name)
                .surname(surname)
                .build());
    }

    public Reader edit(UUID id, String name, String surname) {
        if (readerRepository.existsById(id)) {
            return readerRepository.save(Reader.builder()
                    .id(id)
                    .name(name)
                    .surname(surname)
                    .build());
        }
        return null;
    }

    public void delete(UUID id) {
        if (readerRepository.existsById(id)) {
            readerRepository.deleteById(id);
        }
    }

    public Optional<Reader> getTop(String from, String to) {
        Timestamp f = Objects.equals(from, "") ? Timestamp.valueOf("1970-01-01 00:00:00") : Timestamp.valueOf(LocalDateTime.parse(from));
        Timestamp t = Objects.equals(to, "") ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(LocalDateTime.parse(to));
        return readerRepository.findById(eventRepository.findTopReader(f, t).iterator().next());
    }
    
}
