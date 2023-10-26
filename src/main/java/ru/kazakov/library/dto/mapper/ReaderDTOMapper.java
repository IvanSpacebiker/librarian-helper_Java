package ru.kazakov.library.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import ru.kazakov.library.dto.ReaderDTO;
import ru.kazakov.library.entity.Reader;

@Service
@AllArgsConstructor
public class ReaderDTOMapper implements Function<Reader, ReaderDTO> {
    @Override
    public ReaderDTO apply(Reader key) {
        return new ReaderDTO(key.getId(), key.getName(), key.getSurname());
    }
}
