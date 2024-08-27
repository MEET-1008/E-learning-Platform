package com.codewithmeet.elearningplatform.controllers;

import com.codewithmeet.elearningplatform.dtos.CourseDto;
import com.codewithmeet.elearningplatform.dtos.CustomMessage;
import com.codewithmeet.elearningplatform.dtos.UserDto;
import com.codewithmeet.elearningplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserConroller {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body( userService.addUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("User deleted successfully");
        customMessage.setSuccess(true);
        return ResponseEntity.ok(customMessage);
    }


}
