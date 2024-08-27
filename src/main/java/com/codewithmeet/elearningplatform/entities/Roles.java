package com.codewithmeet.elearningplatform.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private Set<User> users = new HashSet<>();

}
