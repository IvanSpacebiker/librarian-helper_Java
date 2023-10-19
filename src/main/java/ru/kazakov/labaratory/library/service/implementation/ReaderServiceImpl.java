package ru.kazakov.labaratory.library.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.domain.EventRepository;
import ru.kazakov.labaratory.library.domain.ReaderRepository;
import ru.kazakov.labaratory.library.entity.Reader;
import ru.kazakov.labaratory.library.exceptions.NotFoundException;
import ru.kazakov.labaratory.library.service.ReaderService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final EventRepository eventRepository;

    @Override
    public List<Reader> getByNameAndSurname(String name, String surname) {
        return readerRepository.findAllByNameContainingAndSurnameContainingAllIgnoreCase(name, surname);
    }

    @Override
    public Reader getById(UUID id) {
        if (!readerRepository.existsById(id)) {
            throw new NotFoundException("Reader not found");
        }
        return readerRepository.findById(id).get();
    }

    @Override
    public Reader getTop(String from, String to) {
        Timestamp f = Objects.equals(from, "") ? Timestamp.valueOf("1970-01-01 00:00:00") : Timestamp.valueOf(LocalDateTime.parse(from));
        Timestamp t = Objects.equals(to, "") ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(LocalDateTime.parse(to));
        return readerRepository.findById(eventRepository.findTopReader(f, t).iterator().next()).get();
    }

    @Override
    public Reader add(String name, String surname) {

        return readerRepository.save(Reader.builder()
                .id(UUID.randomUUID())
                .name(name)
                .surname(surname)
                .build());
    }

    @Override
    public Reader edit(UUID id, String name, String surname) {
        if (!readerRepository.existsById(id)) {
            throw new NotFoundException("Reader not found");
        }
        return readerRepository.save(Reader.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build());
    }

    @Override
    public void delete(UUID id) {
        if (!readerRepository.existsById(id)) {
            throw new NotFoundException("Reader not found");
        }
        readerRepository.deleteById(id);
    }

}
