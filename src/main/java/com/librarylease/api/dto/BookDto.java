package com.librarylease.api.dto;

import com.librarylease.api.model.Book;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDto {
    private String title;
    private int edition;
    private Instant publishDate;
    private String isbn;
    private String language;
    private Set<AuthorDto> authors;

    public BookDto() {}

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    public static BookDto from(Book book) {
        BookDto dto = new BookDto();

        dto.setTitle(book.getTitle());
        dto.setAuthors(book.getAuthors()
                .stream()
                .map(AuthorDto::from)
                .collect(Collectors.toSet()));
        dto.setIsbn(book.getIsbn10());
        dto.setPublishDate(book.getPublishDate());

        return dto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;
        BookDto bookDto = (BookDto) o;
        return edition == bookDto.edition &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(publishDate, bookDto.publishDate) &&
                Objects.equals(isbn, bookDto.isbn) &&
                Objects.equals(language, bookDto.language) &&
                Objects.equals(authors, bookDto.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, edition, publishDate, isbn, language, authors);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", edition=" + edition +
                ", publishDate=" + publishDate +
                ", isbn='" + isbn + '\'' +
                ", language='" + language + '\'' +
                ", authors=" + authors +
                '}';
    }

}
