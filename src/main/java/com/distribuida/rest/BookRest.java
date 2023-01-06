package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private BookService bookService;
    @Inject private DbConfig dbConfig;
/*
    GET         /books/{id}     listar uno solo
    GET         /books          listar todos
    POST        /books          insertar
    PUT/PATCH   /books/{id}     actualizar (La diferencia entre put y patch es que el put necesita el objeto completo y e patch solo un parámetro)
    DELETE      /books/{id}     eliminar
*/

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll() throws SQLException {

        return bookService.findAll(); };

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer id){

        return bookService.findByID(id);
    };
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBook(Book book){
        bookService.addBook(book.getIsbn().toString(),book.getTitle(),book.getAuthor(),book.getPrice());};

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("id") Integer id, Book book){bookService.updateBook(id,book);};

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void deleteBook(@PathParam("id") Integer id){ bookService.deleteBook(id);};

}
