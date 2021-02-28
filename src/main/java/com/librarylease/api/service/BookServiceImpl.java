package com.librarylease.api.service;

import com.librarylease.api.exception.ResourceNotFoundException;
import com.librarylease.api.model.Author;
import com.librarylease.api.model.Book;
import com.librarylease.api.repository.AuthorRepository;
import com.librarylease.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(new Book("My Book Title"));
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id) {
         return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No Book found with id %d", id)));
    }

    public List<Book> getBooks() {
        List<Book> allBooks = new ArrayList<>();
         bookRepository.findAll()
                 .forEach(book -> allBooks.add(book));
         return allBooks;
    }

    public Book getBookByISBN(String isbn) {
        return bookRepository.findByIsbn13(isbn)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No Book found with isbn %s", isbn)));
    }

    public List<Book> getBooksByAuthor(Author author) {
        Optional<Author> existingAuthor = authorRepository.findByFirstNameAndLastName(
                author.getFirstName(), author.getLastName());

        if (existingAuthor.isPresent()) {
            return bookRepository.findByAuthor(existingAuthor.get().getId());
        } else {
            throw new ResourceNotFoundException(String.format("No Book found for author %s", author.getFullName()));
        }
    }
}
