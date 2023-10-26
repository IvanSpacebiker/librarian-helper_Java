package ru.kazakov.library.dto.mapper;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import ru.kazakov.library.dto.EventDTO;
import ru.kazakov.library.entity.Event;

@Service
@AllArgsConstructor
public class EventDTOMapper implements Function<Event, EventDTO> {
    @Override
    public EventDTO apply(Event key) {
        return new EventDTO(key.getId(), key.getBookid(), key.getReaderid(), key.getType(), key.getTime());
    }
}
