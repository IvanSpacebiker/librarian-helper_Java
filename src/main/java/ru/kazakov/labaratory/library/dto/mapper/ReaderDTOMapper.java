package ru.kazakov.labaratory.library.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import ru.kazakov.labaratory.library.dto.ReaderDTO;
import ru.kazakov.labaratory.library.entity.Reader;

@Service
@AllArgsConstructor
public class ReaderDTOMapper implements Function<Reader, ReaderDTO> {
    @Override
    public ReaderDTO apply(Reader key) {
        return new ReaderDTO(key.getId(), key.getName(), key.getSurname());
    }
}
