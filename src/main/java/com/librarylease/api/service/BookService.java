package com.librarylease.api.service;

import com.librarylease.api.model.Author;
import com.librarylease.api.model.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBook(Long id);
    public List<Book> getBooks();
    Book getBookById(Long id);
    Book getBookByISBN(String isbn);
    List<Book> getBooksByAuthor(Author author);
}
