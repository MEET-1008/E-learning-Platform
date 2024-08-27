package com.codewithmeet.elearningplatform.dtos;

import com.codewithmeet.elearningplatform.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private  String userId;

    private  String name;

    private  String email;

    private  String phoneNumber;

    private  String password;

    private  String about;

    private  boolean active;

    private  boolean emailVarified;

    private  boolean smsVerified;

    private Date createAt;

    private  String profilePath;

    private  String recentOTP;

    private Set<Roles> roles = new HashSet<>();
}
