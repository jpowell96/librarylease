package com.librarylease.api.repository;

import com.librarylease.api.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByIsbn10(String isbn10);
    Optional<Book> findByIsbn13(String isbn13);

    @Query("SELECT b FROM " +
            "Book b INNER JOIN b.authors author" +
            " WHERE author.id = ?1 ")
    List<Book> findByAuthor(Long authorId);

}
