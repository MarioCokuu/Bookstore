package com.example.Ennova.Bookstore.controller;

import com.example.Ennova.Bookstore.dto.BookDto;
import com.example.Ennova.Bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    @Autowired
    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @PostMapping("/add-new-book")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBook(@Validated @RequestBody BookDto bookDto) {
        bookStoreService.addNewBook(bookDto);
    }


    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookStoreService.getBookById(id);
    }

    @GetMapping("/book-list")
    public List<BookDto> getAllBooks() {
        return bookStoreService.getAllBooks();
    }

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id,
                           @Validated @RequestBody BookDto bookDto) {
        bookStoreService.updateBook(id, bookDto);
    }

}
