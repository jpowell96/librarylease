package com.librarylease.api.model;


import com.librarylease.api.dto.BookDto;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private int edition;

    @Column
    private Instant publishDate;

    @Column
    private String isbn10;

    @Column
    private String isbn13;

    @Column
    private String language;

    @Column
    private String imageUrl;

    public Book() {}


    @ManyToMany(cascade = {
            // TODO: What does CascadeType.Persist mean?
            CascadeType.PERSIST,
            // TODO: What does CascadeType.Merge mean?
            CascadeType.MERGE
    })
    @JoinTable(name="book_author",
        joinColumns = {
                @JoinColumn(
                        name = "book_id",
                        referencedColumnName = "id"
                )
        },
        inverseJoinColumns = {
            @JoinColumn(
                    name="author_id",
                    referencedColumnName = "id"
            )
        }
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy="book")
    private Set<UserCatalogBook> userCatalogBooks;

    public Book(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public Instant getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }


    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public static Book from(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setEdition(bookDto.getEdition());
        book.setLanguage(bookDto.getLanguage());
        book.setPublishDate(bookDto.getPublishDate());
        book.setAuthors(bookDto.getAuthors()
                .stream()
                .map(dto -> Author.from(dto))
                .collect(Collectors.toSet()));

        return book;
    }
}
