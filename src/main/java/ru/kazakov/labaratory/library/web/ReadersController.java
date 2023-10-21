package ru.kazakov.labaratory.library.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.labaratory.library.dto.ReaderDTO;
import ru.kazakov.labaratory.library.dto.mapper.ReaderDTOMapper;
import ru.kazakov.labaratory.library.service.implementation.ReaderServiceImpl;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/readers")
public class ReadersController {

    private ReaderServiceImpl service;
    private ReaderDTOMapper readerDTOMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDTO>> getReaderByNameAndSurname(@RequestParam(defaultValue = "") String name,
                                                                     @RequestParam(defaultValue = "") String surname)
    {
        return ResponseEntity.ok(
                service.getByNameAndSurname(name, surname).stream().map(readerDTOMapper::apply).toList()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReaderDTO> getReaderById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(
                readerDTOMapper.apply(service.getById(id))
        );
    }

    @GetMapping("/top")
    public ResponseEntity<ReaderDTO> getTopReader(@RequestParam(defaultValue = "") String from,
                                         @RequestParam(defaultValue = "") String to)
    {
        return ResponseEntity.ok(
                readerDTOMapper.apply(service.getTop(from, to))
        );
    }
    @PostMapping
    public ResponseEntity<ReaderDTO> addReader(@RequestParam String name,
                        @RequestParam String surname)
    {
        return ResponseEntity.ok(
                readerDTOMapper.apply(service.add(name, surname))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderDTO> editReader(@PathVariable UUID id,
                         @RequestParam(defaultValue = "") String name,
                         @RequestParam(defaultValue = "") String surname)
    {
        return ResponseEntity.ok(
                readerDTOMapper.apply(service.edit(id, name, surname))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable UUID id)
    {
        service.delete(id);
    }
}
