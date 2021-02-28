package com.librarylease.api.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.librarylease.api.model.UserCatalogBook;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private final String email;
    private final String username;
    
    @JsonIgnore
    private final String password;

    @ManyToMany(cascade = {
            // TODO: What does CascadeType.Persist mean?
            CascadeType.PERSIST,
            // TODO: What does CascadeType.Merge mean?
            CascadeType.MERGE
    })
    @JoinTable(name="user_role",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name="role_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private final Set<ApplicationUserRole> roles;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private Set<UserCatalogBook> books;

    public ApplicationUser(String email,
                           String username,
                           String password,
                           Set<ApplicationUserRole> roles,
                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Set<ApplicationUserRole> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = roles.stream()
                .flatMap(role -> role.getAuthority().getGrantedAuthorities().stream())
                .collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
