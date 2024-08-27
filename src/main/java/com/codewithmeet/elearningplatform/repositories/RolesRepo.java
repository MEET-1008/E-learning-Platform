package com.codewithmeet.elearningplatform.repositories;

import com.codewithmeet.elearningplatform.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<Roles, String> {
    Optional<Roles> findByRoleName(String roleName);
}
