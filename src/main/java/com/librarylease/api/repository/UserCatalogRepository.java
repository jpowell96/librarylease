package com.librarylease.api.repository;

import com.librarylease.api.model.UserCatalogBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCatalogRepository extends CrudRepository<UserCatalogBook, Long> {

    // Find by User Id:
    List<UserCatalogBook> findByUserId(Long userId);
    List<UserCatalogBook> findByBookId(Long bookId);
}
