package com.librarylease.api.service;

import com.librarylease.api.model.UserCatalogBook;
import com.librarylease.api.repository.UserCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCatalogService {
    private final UserCatalogRepository userCatalogRepository;

    @Autowired
    public UserCatalogService(UserCatalogRepository userCatalogRepository) {
        this.userCatalogRepository = userCatalogRepository;
    }

    // Get, Update, Delete

    public UserCatalogBook createUserCatalogEntry(UserCatalogBook userCatalogBook) {
       return userCatalogRepository.save(userCatalogBook);
    }

    public List<UserCatalogBook> getUserCatalog(Long userId) {
       return userCatalogRepository.findByUserId(userId);
    }

    public List<UserCatalogBook> getCatalogByBook(Long bookId) {
        return userCatalogRepository.findByBookId(bookId);
    }
}
