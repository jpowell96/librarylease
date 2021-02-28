package com.librarylease.api.repository;

import com.librarylease.api.auth.ApplicationUserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<ApplicationUserRole, Long> {
}
