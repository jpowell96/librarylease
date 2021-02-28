package com.librarylease.api.auth;

import com.librarylease.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository applicationUserDao, PasswordEncoder passwordEncoder) {
        this.userRepository = applicationUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found" ,s)));
    }

    public ApplicationUser createUser(ApplicationUser applicationUser) {
       return userRepository.save(applicationUser);
    }

    public boolean isUserNameTaken(String username) {
        Optional<ApplicationUser> maybeUser = userRepository.findByUsername(username);
        return maybeUser.isPresent();
    }

    public boolean isEmailTaken(String email) {
        Optional<ApplicationUser> maybeUser = userRepository.findByEmail(email);
        return maybeUser.isPresent();
    }
}
