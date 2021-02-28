package com.librarylease.api.repository;

import com.librarylease.api.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
