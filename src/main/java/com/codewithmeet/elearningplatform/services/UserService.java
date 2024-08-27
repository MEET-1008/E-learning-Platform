package com.codewithmeet.elearningplatform.services;

import com.codewithmeet.elearningplatform.dtos.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto getUserById(String id);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto userDto , String id);
    void deleteUser(String id);
    UserDto getUserByEmail(String email);
}
