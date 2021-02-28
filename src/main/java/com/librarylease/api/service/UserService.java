package com.librarylease.api.service;

import com.librarylease.api.auth.ApplicationUser;

public interface UserService {

    public void registerNewUser(ApplicationUser user);
}
