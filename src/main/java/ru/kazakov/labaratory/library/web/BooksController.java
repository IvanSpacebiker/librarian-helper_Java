package ru.kazakov.labaratory.library.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kazakov.labaratory.library.dto.BookDTO;
import ru.kazakov.labaratory.library.dto.mapper.BookDTOMapper;
import ru.kazakov.labaratory.library.service.implementation.BookServiceImpl;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BooksController {

    private BookServiceImpl service;
    private BookDTOMapper bookDTOMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBookByTitleAndAuthor(@RequestParam(defaultValue = "") String title,
                                                                 @RequestParam(defaultValue = "") String author)
    {
        return ResponseEntity.ok(
                service.getByTitleAndAuthor(title, author).stream().map(bookDTOMapper::apply).toList()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(
                bookDTOMapper.apply(service.getById(id))
        );
    }

    @GetMapping("/top")
    public ResponseEntity<BookDTO> getTopBook(@RequestParam(defaultValue = "") String from,
                                     @RequestParam(defaultValue = "") String to) {
        return ResponseEntity.ok(
                bookDTOMapper.apply(service.getTop(from, to))
        );
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestParam String title,
                        @RequestParam String author,
                        @RequestParam(defaultValue = "1") int quantity)
    {
        return ResponseEntity.ok(
                bookDTOMapper.apply(service.add(title, author, quantity))
        );
    }



    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> editBook(@PathVariable UUID id,
                         @RequestParam(defaultValue = "") String title,
                         @RequestParam(defaultValue = "") String author,
                         @RequestParam(defaultValue = "-1") int quantity)
    {
        return ResponseEntity.ok(
                bookDTOMapper.apply(service.edit(id, title, author, quantity))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id)
    {
        service.delete(id);
    }

}
