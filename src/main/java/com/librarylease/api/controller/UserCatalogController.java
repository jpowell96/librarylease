package com.librarylease.api.controller;

import com.librarylease.api.auth.ApplicationUser;
import com.librarylease.api.model.UserCatalogBook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/catalog")
@RestController
public class UserCatalogController {
    // User Catalog Service

    public UserCatalogController() {

    }

    // Create requires user auth
    @PostMapping
    public UserCatalogBook addUserCatalogEntry(UserCatalogBook book, ApplicationUser user) {
        return null;
    }

}
