package com.manage.restaurant.controller.login;

import com.manage.restaurant.dto.LoginRequest;
import com.manage.restaurant.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, String> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        // Authenticate user with Spring Security
        System.out.println("ashduasduasduisayduisadisau");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Generate JWT if authentication is successful
        String token = jwtUtil.generateToken(authentication.getName());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

}