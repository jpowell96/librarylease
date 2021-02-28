package com.librarylease.api.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum ApplicationUserAuthority {
    USER("USER", Collections.emptySet()),
    LIBRARY_ADMIN("LIBRARY_ADMIN", Collections.emptySet()),
    ADMIN("ADMIN", Collections.emptySet()),
    ANONYMOUS("ANONYMOUS", Collections.emptySet());

    private final String role;
    private final Set<String> permissions;

    ApplicationUserAuthority(String role, Set<String> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    String getRole() {
        return role;
    }

    public static ApplicationUserAuthority fromRoleName(String roleName) {
        switch (roleName) {
            case "USER":
                return ApplicationUserAuthority.USER;

            case "LIBRARY_ADMIN":
                return ApplicationUserAuthority.LIBRARY_ADMIN;

            case "ADMIN":
                return ApplicationUserAuthority.ADMIN;
            default:
                throw new IllegalArgumentException("ApplicationUserAuthority [" + roleName
                        + "] not supported.");
        }
    }

    /**
     *
     * For the given role, grabs the permissions (BOOK_READ, BOOK_WRITE, etc. )for the role
     * and makes a set of 'GrantedAuthorities'.
     *
     * Then we add the role name at the end.
     *
     * This method will let us associate a role with the endpoints it can hit
     * that we specify (for now) in the ApplicationSecurityConfig antMatchers
     *
     *
     *
     * */
    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> perms = new HashSet<>();
        perms.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return perms;
    }
}
