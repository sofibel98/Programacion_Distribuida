package com.distribuida.db;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//Genera getters y setter para todos los elementos @Data
@Data
public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private Double price;
}
