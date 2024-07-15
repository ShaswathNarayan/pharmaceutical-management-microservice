package com.pharmaceutical.controller;

import com.pharmaceutical.model.Users;
import com.pharmaceutical.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for managing user information in the pharmaceutical application.
 */
@RestController
@RequestMapping("/pharmaceutical/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * Get all users.
     *
     * @return a ResponseEntity containing the status and list of all users.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = usersService.getAllUsers();
        return createResponseEntity(response);
    }

    /**
     * Get a user by their ID.
     *
     * @param id the ID of the user.
     * @return a ResponseEntity containing the status and the user data if found.
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        Map<String, Object> response = usersService.getUserById(id);
        return createResponseEntity(response);
    }

    /**
     * Get a user by their username.
     *
     * @param username the username of the user.
     * @return a ResponseEntity containing the status and the user data if found.
     */
    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username) {
        Map<String, Object> response = usersService.getUserByUsername(username);
        return createResponseEntity(response);
    }

    /**
     * Create a new user.
     *
     * @param user the information of the new user.
     * @return a ResponseEntity containing the status and the created user data.
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Users user) {
        try {
            Map<String, Object> response = usersService.createUser(user);
            return createResponseEntity(response);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() != null && e.getCause().getCause() != null 
                    && e.getCause().getCause().getMessage().contains("Duplicate entry")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("status", "error", "message", "User already exists."));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", "Internal server error."));
        }
    }

    /**
     * Update an existing user.
     *
     * @param id the ID of the user to be updated.
     * @param user the updated information of the user.
     * @return a ResponseEntity containing the status and the updated user data if successful.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Map<String, Object> response = usersService.updateUser(id, user);
        return createResponseEntity(response);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to be deleted.
     * @return a ResponseEntity containing the status and deletion confirmation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = usersService.deleteUser(id);
        return createResponseEntity(response);
    }

    /**
     * Helper method to create a ResponseEntity based on the response map.
     *
     * @param response the response map containing status and data/message.
     * @return a ResponseEntity with appropriate status and body.
     */
    private ResponseEntity<Map<String, Object>> createResponseEntity(Map<String, Object> response) {
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else if ("error".equals(response.get("status"))) {
            String message = (String) response.get("message");
            if ("User not found.".equals(message)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else if ("User already exists.".equals(message)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
