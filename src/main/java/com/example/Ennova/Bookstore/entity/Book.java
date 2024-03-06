package com.example.Ennova.Bookstore.entity;

import com.example.Ennova.Bookstore.enums.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    private Type type;

    @NotNull
    private double price;

    public void getPrice(double v) {
    }
}
