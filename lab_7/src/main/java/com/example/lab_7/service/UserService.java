package com.example.lab_7.service;

import com.example.lab_7.entities.MyUser;
import com.example.lab_7.daos.MyUserRepository;
import com.example.lab_7.service.exceptions.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final MyUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserService(MyUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create a new user
    public MyUser createUser(MyUser user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        return userRepository.save(user);
    }

    // Reset a user's password
    public void resetPassword(String email, String newPassword) {
        MyUser user = userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // Toggle a user's locked/unlocked status
    public MyUser toggleUnlockStatus(String email) {
        MyUser user = userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        user.setAccountNonLocked(!user.isAccountNonLocked());
        return userRepository.save(user);
    }

    // Delete a user by email
    public void deleteUser(String email) {
        if (!userRepository.existsById(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        userRepository.deleteById(email);
    }

    // Get all users
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by email
    public MyUser getUserByEmail(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }
}
