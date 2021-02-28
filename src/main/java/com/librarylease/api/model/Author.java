package com.librarylease.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.librarylease.api.dto.AuthorDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(mappedBy = "authors", cascade = {
            // TODO: What does CascadeType.Persist mean?
            CascadeType.PERSIST,
            // TODO: What does CascadeType.Merge mean?
            CascadeType.MERGE
    })
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Author(){}

    public static Author from(AuthorDto authorDto) {
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());

        return author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() { return String.format("%s %s", this.firstName, this.lastName);}

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
