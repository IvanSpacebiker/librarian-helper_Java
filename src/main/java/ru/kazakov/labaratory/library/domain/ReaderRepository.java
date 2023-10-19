package ru.kazakov.labaratory.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kazakov.labaratory.library.entity.Reader;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, UUID> {

    List<Reader> findAllByNameContainingAndSurnameContainingAllIgnoreCase(String name, String surname);

}
