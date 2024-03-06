package com.example.Ennova.Bookstore.serviceImpl;

import com.example.Ennova.Bookstore.dto.BookDto;
import com.example.Ennova.Bookstore.entity.Book;
import com.example.Ennova.Bookstore.repository.BookRepository;
import com.example.Ennova.Bookstore.service.BookStoreService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    public BookStoreServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void addNewBook(BookDto bookDto) {

        Optional<Book> bookById = bookRepository.findById(bookDto.getId());
        bookById.ifPresent(book -> {
            throw new IllegalArgumentException("Book with same id present. ");
        });
        if (!bookById.isPresent()) {
            Book book = modelMapper.map(bookDto, Book.class);
            bookRepository.save(book);
        }
    }


    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find Book"));

        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return mapBookListToBooDtoList(books);
    }

    @Override
    @Transactional
    public void updateBook(Long id, BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        if (bookDto.getId() != null && !bookDto.getId().equals(id)) {
                throw new RuntimeException("Id cannot be updated.");
        }
        book.setId(id);
        bookRepository.save(book);

    }
    private List<BookDto> mapBookListToBooDtoList(List<Book> books) {
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

}
