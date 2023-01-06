package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import org.jdbi.v3.core.Jdbi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped

public class BookServiceImpl implements BookService{
    List<Book> books = new ArrayList<>();
   public Book findByID(Integer id){
       DbConfig nuevo = new DbConfig();
       nuevo.test();
       Jdbi jdbi = nuevo.jdbi;

       List<Book> books = jdbi.withHandle(handle -> {
           // Easy mapping to any type
           return handle.createQuery("SELECT * FROM public.books WHERE id = :id")
                   .bind("id",id)
                   .mapToBean(Book.class)
                   .list();
       });

       return books.get(0);
   };
    public List<Book> findAll() throws SQLException {
        DbConfig nuevo = new DbConfig();
        nuevo.test();
        Jdbi jdbi = nuevo.jdbi;

        List<Book> books = jdbi.withHandle(handle -> {
            // Easy mapping to any type
            return handle.createQuery("SELECT * FROM public.books")
                    .mapToBean(Book.class)
                    .list();
        });

        return books;
    }
    public void addBook(String isbn, String title, String author, double price) {
        DbConfig nuevo = new DbConfig();
        nuevo.test();
        Jdbi jdbi = nuevo.jdbi;
        int state = jdbi.withHandle(handle -> {
            return handle.createUpdate("INSERT INTO books (isbn, title, author, price) VALUES (:isbn, :title, :author, :price)")
                    .bind("isbn", isbn)
                    .bind("title", title)
                    .bind("author", author)
                    .bind("price", price)
                    .execute();
        });
    }
    public void updateBook(Integer id, Book book) {
        DbConfig nuevo = new DbConfig();
        nuevo.test();
        Jdbi jdbi = nuevo.jdbi;
        jdbi.useHandle(handle -> {

            if (book.getIsbn() != ""){
                handle.execute("UPDATE books SET isbn = ? WHERE id = ?",book.getIsbn(), id);
            }

            if (book.getTitle() != ""){
                handle.execute("UPDATE books SET title = ? WHERE id = ?",book.getTitle(), id);
            }

            if (book.getAuthor() != ""){
                handle.execute("UPDATE books SET author = ? WHERE id = ?",book.getAuthor(), id);
            }

            if (book.getPrice() != null){
                handle.execute("UPDATE books SET price = ? WHERE id = ?",book.getPrice(), id);
            }
        });

    }
    public void deleteBook(Integer id) {
        DbConfig nuevo = new DbConfig();
        nuevo.test();
        Jdbi jdbi = nuevo.jdbi;
        int state = jdbi.withHandle(handle -> {
            return handle.createUpdate("DELETE FROM books WHERE id = :id")
                    .bind("id", id)
                    .execute();

        });
    }

}
