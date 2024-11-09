package com.codewithmeet.elearningplatform.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private  String token;

    private  UserDto user;
}