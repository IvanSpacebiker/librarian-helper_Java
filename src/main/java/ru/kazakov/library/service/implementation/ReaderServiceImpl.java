package ru.kazakov.library.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kazakov.library.domain.EventRepository;
import ru.kazakov.library.domain.ReaderRepository;
import ru.kazakov.library.entity.Reader;
import ru.kazakov.library.exceptions.NotFoundException;
import ru.kazakov.library.service.ReaderService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final EventRepository eventRepository;

    @Override
    public List<Reader> getReaderByNameAndSurname(String name, String surname) {
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
        if (eventRepository.findTopReader(f, t).isEmpty()) throw new NotFoundException("Readers not found");
        return readerRepository.findById(eventRepository.findTopReader(f, t).iterator().next()).get();
    }

    @Override
    public Reader addReader(String name, String surname) {

        return readerRepository.save(Reader.builder()
                .id(UUID.randomUUID())
                .name(name)
                .surname(surname)
                .build());
    }

    @Override
    public Reader editReader(UUID id, String name, String surname) {
        if (!readerRepository.existsById(id)) {
            throw new NotFoundException("Reader not found");
        }

        Reader reader = readerRepository.findById(id).get();
        if (!Objects.equals(name, "")) reader.setName(name);
        if (!Objects.equals(surname, "")) reader.setSurname(surname);

        return readerRepository.save(reader);
    }

    @Override
    public void delete(UUID id) {
        if (!readerRepository.existsById(id)) {
            throw new NotFoundException("Reader not found");
        }
        readerRepository.deleteById(id);
    }

}
