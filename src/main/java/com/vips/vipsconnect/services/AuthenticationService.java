package com.vips.vipsconnect.services;

import com.vips.vipsconnect.models.User;
import com.vips.vipsconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    // Inject the UserRepository to access the database
    @Autowired
    private UserRepository userRepository;

    // Inject the BCryptPasswordEncoder for password hashing comparison
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Method to authenticate user based on email and password
    public User login(String email, String password) {
        // Retrieve user from the database by email
        Optional<User> userOptional = userRepository.findByEmail(email);

        // Check if the user exists in the database
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the provided password matches the stored hashed password
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;  // Authentication successful
            }
        }

        return null;  // Authentication failed
    }
}
