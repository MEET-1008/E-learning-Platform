package com.codewithmeet.elearningplatform;

import com.codewithmeet.elearningplatform.config.AppConstants;
import com.codewithmeet.elearningplatform.entities.Roles;
import com.codewithmeet.elearningplatform.entities.User;
import com.codewithmeet.elearningplatform.repositories.RolesRepo;
import com.codewithmeet.elearningplatform.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class ELearningPlatformApplication implements CommandLineRunner {


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;

    @Autowired
    RolesRepo rolesRepo;


    public static void main(String[] args) {
        SpringApplication.run(ELearningPlatformApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Roles roles1 = new Roles();
        roles1.setRoleId(UUID.randomUUID().toString());
        roles1.setRoleName(AppConstants.ROLE_ADMIN);

        Roles roles2 = new Roles();
        roles2.setRoleId(UUID.randomUUID().toString());
        roles2.setRoleName(AppConstants.ROLE_USER);

        Roles roles3 = new Roles();
        roles3.setRoleId(UUID.randomUUID().toString());
        roles3.setRoleName(AppConstants.ROLE_TEACHER);

        rolesRepo.findByRoleName(AppConstants.ROLE_ADMIN).ifPresentOrElse(e -> {
            roles1.setRoleId(e.getRoleId());
            System.out.println("Admin role found in database...!");
        }, () -> {
            rolesRepo.save(roles1);
        });
        rolesRepo.findByRoleName(AppConstants.ROLE_USER).ifPresentOrElse(e -> {
            roles2.setRoleId(e.getRoleId());
            System.out.println("user role found in database...!");
        }, () -> {
            rolesRepo.save(roles2);
        });
        rolesRepo.findByRoleName(AppConstants.ROLE_TEACHER).ifPresentOrElse(e -> {
            roles3.setRoleId(e.getRoleId());
            System.out.println("teacher role found in database...!");
        }, () -> {
            rolesRepo.save(roles3);
        });


        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail("admin@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        user.addRole(roles1);

        if (userRepo.findByEmail("admin@gmail.com").isPresent()) {
            System.out.println("User already exists");
        } else {
            userRepo.save(user);
        }

        User user1 = new User();
        user1.setUserId(UUID.randomUUID().toString());
        user1.setEmail("teacher@gmail.com");
        user1.setPassword(passwordEncoder.encode("123"));
        user1.addRole(roles3);

        if (userRepo.findByEmail("teacher@gmail.com").isPresent()) {
            System.out.println("teacher already exists");
        } else {
            userRepo.save(user1);
        }

        User user2 = new User();
        user2.setUserId(UUID.randomUUID().toString());
        user2.setEmail("user@gmail.com");
        user2.setPassword(passwordEncoder.encode("123"));
        user2.addRole(roles2);

        if (userRepo.findByEmail("user@gmail.com").isPresent()) {
            System.out.println("user already exists");
        } else {
            userRepo.save(user2);
        }

    }
}
