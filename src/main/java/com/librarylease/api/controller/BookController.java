package com.librarylease.api.controller;

import com.librarylease.api.dto.AuthorDto;
import com.librarylease.api.dto.BookDto;
import com.librarylease.api.model.Author;
import com.librarylease.api.model.Book;
import com.librarylease.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getBooks()
                .stream()
                .map(BookDto::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Book createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(Book.from(bookDto));
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/author")
    public List<Book> getBookByAuthor(AuthorDto authorDto) {
        Author author = Author.from(authorDto);
        return bookService.getBooksByAuthor(author);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}