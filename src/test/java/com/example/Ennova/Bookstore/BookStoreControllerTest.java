package com.example.Ennova.Bookstore;

import com.example.Ennova.Bookstore.controller.BookStoreController;
import com.example.Ennova.Bookstore.dto.BookDto;
import com.example.Ennova.Bookstore.service.BookStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookStoreControllerTest {

    @Mock
    private BookStoreService bookStoreService;

    @InjectMocks
    private BookStoreController bookStoreController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
    }

    @Test
    void addNewBook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setTitle("Test Book");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/add-new-book")
                        .contentType("application/json")
                        .content("{\"id\": 1, \"title\": \"Test Book\"}"))
                .andExpect(status().isCreated());

        verify(bookStoreService).addNewBook(any());
    }

    @Test
    void getBookById() throws Exception {
        when(bookStoreService.getBookById(1L)).thenReturn(new BookDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(bookStoreService).getBookById(1L);
    }

    @Test
    void getAllBooks() throws Exception {
        when(bookStoreService.getAllBooks()).thenReturn(Collections.singletonList(new BookDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/book-list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(bookStoreService).getAllBooks();
    }

    @Test
    void updateBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType("application/json")
                        .content("{\"id\": 1, \"title\": \"Updated Book\"}"))
                .andExpect(status().isOk());

        verify(bookStoreService).updateBook(eq(1L), any());
    }
}

