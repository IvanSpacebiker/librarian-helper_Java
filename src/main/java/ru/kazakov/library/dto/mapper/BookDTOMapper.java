package ru.kazakov.library.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import ru.kazakov.library.dto.BookDTO;
import ru.kazakov.library.entity.Book;

@Service
@AllArgsConstructor
public class BookDTOMapper implements Function<Book, BookDTO> {

    @Override
    public BookDTO apply(Book key) {
        return new BookDTO(key.getId(), key.getTitle(), key.getAuthor(), key.getQuantity());
    }
}
