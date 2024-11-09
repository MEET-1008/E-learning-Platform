package com.codewithmeet.elearningplatform;

import com.codewithmeet.elearningplatform.config.JWT.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ELearningPlatformApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    JwtUtil jwtUtil;

    @Test
    public void testJwt() {

        System.out.println("jwt testing...");

        String token = jwtUtil.generateToken("user@gmail.com");

        System.out.println("token: " + token);

        Boolean b = jwtUtil.validateToken(token, "user@gmail.com");
        System.out.println("validation: " + b);

        String s = jwtUtil.extractUsername(token);
        System.out.println("username: " + s);


    }

}
