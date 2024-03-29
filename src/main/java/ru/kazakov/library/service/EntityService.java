package ru.kazakov.library.service;

import java.util.UUID;

public interface EntityService<T> {

    T getById(UUID id);
    T getTop(String from, String to);
    void delete(UUID id);
}
