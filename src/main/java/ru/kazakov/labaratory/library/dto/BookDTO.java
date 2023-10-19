package ru.kazakov.labaratory.library.dto;

import java.util.UUID;

public record BookDTO(UUID id, String title, String author, Integer quantity) {}
