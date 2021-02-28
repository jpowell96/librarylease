package com.librarylease.api;

import com.librarylease.api.auth.ApplicationUser;
import com.librarylease.api.auth.ApplicationUserAuthority;
import com.librarylease.api.auth.ApplicationUserRole;
import com.librarylease.api.model.Author;
import com.librarylease.api.model.Book;
import com.librarylease.api.repository.BookRepository;
import com.librarylease.api.repository.RoleRepository;
import com.librarylease.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This is an Application runner that runs right after application startup.
 *
 * For dev, this will populate the tables with a few values to make it
 * easier to test stuff.
 *
 * */
@Profile("dev")
@Component
public class DevApplicationRunner implements ApplicationRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DevApplicationRunner(BookRepository bookRepository,
                                UserRepository userRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    private void setupRoles() {
        ApplicationUserRole USER = new ApplicationUserRole();
        USER.setAuthority(ApplicationUserAuthority.USER);

        ApplicationUserRole LIBRARY_ADMIN = new ApplicationUserRole();
        LIBRARY_ADMIN.setAuthority(ApplicationUserAuthority.LIBRARY_ADMIN);

        ApplicationUserRole ADMIN = new ApplicationUserRole();
        ADMIN.setAuthority(ApplicationUserAuthority.ADMIN);

        roleRepository.saveAll(List.of(USER, LIBRARY_ADMIN, ADMIN));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        setupRoles();
        Book book = new Book();
        book.setTitle("Bitcoin and Black America");
        book.setPublishDate(Instant.now());

        Author author = new Author();
        author.setFirstName("Jonathan");
        author.setLastName("Powell");

        book.setAuthors(Set.of(author));
        bookRepository.save(book);

        ApplicationUser applicationUser = new ApplicationUser(
                "jpowell@gmail.com",
                "itsjjpowell",
                passwordEncoder.encode("password")
                ,
                Collections.emptySet(),
                true,
                true,
                true,
                true);

        userRepository.save(applicationUser);
    }
}
