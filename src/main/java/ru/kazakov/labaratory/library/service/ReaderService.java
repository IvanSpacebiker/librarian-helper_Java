package ru.kazakov.labaratory.library.service;

import ru.kazakov.labaratory.library.entity.Reader;

import java.util.List;
import java.util.UUID;

public interface ReaderService extends EntityService<Reader> {

    List<Reader> getByNameAndSurname(String name, String surname);
    Reader add(String name, String surname);
    Reader edit(UUID id, String name, String surname);
}
