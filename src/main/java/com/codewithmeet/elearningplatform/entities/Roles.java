package com.codewithmeet.elearningplatform.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Roles {

    @Id
    private String roleId;

    private String roleName;

    @ManyToMany()
    @JoinTable(name = "roles_users")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

}
