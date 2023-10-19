package ru.kazakov.labaratory.library.dto;

import ru.kazakov.labaratory.library.entity.EventType;

import java.sql.Timestamp;
import java.util.UUID;

public record EventDTO(UUID id, UUID bookid, UUID readerid, EventType type, Timestamp time) {}
