package ru.kazakov.labaratory.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.labaratory.library.entity.Reader;
import ru.kazakov.labaratory.library.service.ReaderService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/readers")
public class ReadersController {

    @Autowired
    private ReaderService service;

    @GetMapping
    public Iterable<Reader> getReaderByTitleAndAuthor(@RequestParam(defaultValue = "") String name,
                                                  @RequestParam(defaultValue = "") String surname)
    {
        return service.getByNameAndSurname(name, surname);
    }
    @GetMapping("/{id}")
    public Optional<Reader> getReaderById(@PathVariable UUID id)
    {
        return service.getById(id);
    }
    @PostMapping
    public Reader addReader(@RequestParam String name,
                        @RequestParam String surname)
    {
        return service.add(name, surname);
    }

    @PutMapping("/{id}")
    public Reader editReader(@PathVariable UUID id,
                         @RequestParam String name,
                         @RequestParam String surname)
    {
        return service.edit(id, name, surname);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable UUID id)
    {
        service.delete(id);
    }
}
