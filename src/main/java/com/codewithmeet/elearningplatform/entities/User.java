package com.codewithmeet.elearningplatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String password;

    private String about;

    private boolean active;

    private boolean emailVarified;

    private boolean smsVerified;

    private Date createAt;

    private String profilePath;

    private String recentOTP;


    @ManyToMany(mappedBy = "users" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();


    public void addRole(Roles role) {
        roles.add(role);
        role.getUsers().add(this);
    }
    public void removeRole(Roles role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

}
