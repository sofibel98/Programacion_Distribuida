package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    Book findByID(Integer id);
    List<Book> findAll() throws SQLException;
    void addBook(String isbn, String title, String author, double price);
    void updateBook(Integer id, Book book);
    void deleteBook(Integer id);

}
