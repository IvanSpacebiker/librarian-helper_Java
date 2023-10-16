package ru.kazakov.labaratory.library.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MenuController {

    @GetMapping
    public String getMenu() {
        return "<a href=\"/books\">books</a>";
    }
}
