package com.example.Ennova.Bookstore.service;

import com.example.Ennova.Bookstore.dto.BookDto;

import java.util.List;

public interface BookStoreService {

    void addNewBook(BookDto bookDto);

    BookDto getBookById(Long id);

    List<BookDto> getAllBooks();

    void updateBook(Long id, BookDto bookDto);
}
