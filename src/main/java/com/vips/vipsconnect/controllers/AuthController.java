package com.vips.vipsconnect.controllers;

import com.vips.vipsconnect.models.LoginUser;
import com.vips.vipsconnect.models.User;
import com.vips.vipsconnect.repositories.UserRepository;
import com.vips.vipsconnect.security.JwtTokenProvider;
import com.vips.vipsconnect.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());

        // Default to STUDENT if not provided
        user.setRole(request.getRole() != null ? request.getRole().toUpperCase() : "ROLE_STUDENT");

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser loginRequest) {
        User user = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());
            return ResponseEntity.ok(
                    Map.of(
                            "token", token,
                            "email", user.getEmail(),
                            "role", user.getRole()
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


}
