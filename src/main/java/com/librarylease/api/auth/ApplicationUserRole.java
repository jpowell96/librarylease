package com.librarylease.api.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="roles")
public class ApplicationUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ApplicationUserAuthority authority;

    @ManyToMany(mappedBy = "roles", cascade = {
            // TODO: What does CascadeType.Persist mean?
            CascadeType.PERSIST,
            // TODO: What does CascadeType.Merge mean?
            CascadeType.MERGE
    })
    @JsonIgnore
    private Set<ApplicationUser> users;

    public ApplicationUserRole() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUserAuthority getAuthority() {
        return authority;
    }

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationUser> users) {
        this.users = users;
    }

    public void setAuthority(ApplicationUserAuthority authority) {
        this.authority = authority;
    }
}
