package com.example.Ennova.Bookstore.dto;

import com.example.Ennova.Bookstore.enums.Type;
import lombok.Data;

@Data
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private Type type;

    private float price;
}
