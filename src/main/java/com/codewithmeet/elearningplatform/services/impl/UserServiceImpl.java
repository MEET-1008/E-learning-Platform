package com.codewithmeet.elearningplatform.services.impl;

import com.codewithmeet.elearningplatform.config.AppConstants;
import com.codewithmeet.elearningplatform.dtos.UserDto;
import com.codewithmeet.elearningplatform.entities.Roles;
import com.codewithmeet.elearningplatform.entities.User;
import com.codewithmeet.elearningplatform.exceptions.ResourceNotFoundException;
import com.codewithmeet.elearningplatform.repositories.RolesRepo;
import com.codewithmeet.elearningplatform.repositories.UserRepo;
import com.codewithmeet.elearningplatform.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RolesRepo rolesRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> allUser = userRepo.findAll(pageable);
        List<UserDto> userDtos = allUser.getContent()
                .stream()
                .map(e -> modelMapper.map(e, UserDto.class))
                .toList();
        return new PageImpl<>(userDtos, pageable, allUser.getTotalElements());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setUserId(UUID.randomUUID().toString());
        user.setCreateAt(new Date());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Roles roles = rolesRepo.findByRoleName(AppConstants.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.addRole(roles);
        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAbout(userDto.getAbout());
        userRepo.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }
}
