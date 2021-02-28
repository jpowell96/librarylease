package com.librarylease.api.dto;

import com.librarylease.api.model.Author;

import java.util.Objects;

public class AuthorDto {

    private String firstName;
    private String lastName;

    public AuthorDto() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorDto)) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(firstName, authorDto.firstName) &&
                Objects.equals(lastName, authorDto.lastName);
    }

    public static AuthorDto from(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        return authorDto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
