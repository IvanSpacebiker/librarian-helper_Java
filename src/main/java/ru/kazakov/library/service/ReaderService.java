package ru.kazakov.library.service;

import ru.kazakov.library.entity.Reader;

import java.util.List;
import java.util.UUID;

public interface ReaderService extends EntityService<Reader> {

    List<Reader> getReaderByNameAndSurname(String name, String surname);
    Reader addReader(String name, String surname);
    Reader editReader(UUID id, String name, String surname);
}
