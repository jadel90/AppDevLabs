package com.example.lab_7.controller;

import com.example.lab_7.entities.MyUser;
import com.example.lab_7.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserREST {

    private final UserService userService;

    public UserREST(UserService userService) {
        this.userService = userService;
    }

    // Creates a new user and returns the created user with a 201 (Created) status.
    @PostMapping
    public ResponseEntity<MyUser> createUser(@Valid @RequestBody MyUser user) {
        MyUser createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Retrieves a list of all users and returns it with a 200 (OK) status.
    @GetMapping
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Edit a user's password
    @PatchMapping("/reset-password/{email}")
    public ResponseEntity<String> resetPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        if (!request.containsKey("newPassword") || request.get("newPassword").isEmpty()) {
            return ResponseEntity.badRequest().body("New password must be provided.");
        }
        String newPassword = request.get("newPassword");
        userService.resetPassword(email, newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }

    // Toggle a user's unlocked status
    @PatchMapping("/{email}/toggle-unlock")
    public ResponseEntity<MyUser> toggleUnlockStatus(@PathVariable String email) {
        MyUser updatedUser = userService.toggleUnlockStatus(email);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted successfully.");
    }


}
