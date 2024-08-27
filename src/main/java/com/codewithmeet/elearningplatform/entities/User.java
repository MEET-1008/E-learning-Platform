package com.codewithmeet.elearningplatform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String userId;

    private String name;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
