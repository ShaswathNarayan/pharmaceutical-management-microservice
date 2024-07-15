package com.pharmaceutical.service;

import com.pharmaceutical.model.Users;
import com.pharmaceutical.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class for handling operations related to Users entities.
 */
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Retrieves all users.
     *
     * @return A map containing status and either list of users (if successful) or error message (if failed).
     */
    public Map<String, Object> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Users> users = usersRepository.findAll();
            response.put("status", "success");
            response.put("data", users);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to retrieve users.");
        }
        return response;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A map containing status and either user object (if found) or error message (if not found or failed).
     */
    public Map<String, Object> getUserById(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Users> user = usersRepository.findById(id);
            if (user.isPresent()) {
                response.put("status", "success");
                response.put("data", user.get());
            } else {
                response.put("status", "error");
                response.put("message", "User not found.");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to retrieve user.");
        }
        return response;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return A map containing status and either user object (if found) or error message (if not found or failed).
     */
    public Map<String, Object> getUserByUsername(String username) {
        Map<String, Object> response = new HashMap<>();
        try {
            Users user = usersRepository.findByUsername(username);
            if (user != null) {
                response.put("status", "success");
                response.put("data", user);
            } else {
                response.put("status", "error");
                response.put("message", "User not found.");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to retrieve user.");
        }
        return response;
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to create.
     * @return A map containing status and either created user object (if successful) or error message (if failed).
     */
    public Map<String, Object> createUser(Users user) {
        Map<String, Object> response = new HashMap<>();
        try {
            Users createdUser = usersRepository.save(user);
            response.put("status", "success");
            response.put("data", createdUser);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to create user.");
        }
        return response;
    }

    /**
     * Updates an existing user.
     *
     * @param id   The ID of the user to update.
     * @param user The updated user object.
     * @return A map containing status and either updated user object (if successful) or error message (if not found or failed).
     */
    public Map<String, Object> updateUser(Long id, Users user) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Users> existingUserOptional = usersRepository.findById(id);
            if (existingUserOptional.isPresent()) {
                Users existingUser = existingUserOptional.get();
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                // Set other fields as needed
                Users updatedUser = usersRepository.save(existingUser);
                response.put("status", "success");
                response.put("data", updatedUser);
            } else {
                response.put("status", "error");
                response.put("message", "User not found.");
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to update user.");
        }
        return response;
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A map containing status and either success message (if deletion successful) or error message (if failed).
     */
    public Map<String, Object> deleteUser(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            usersRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "User deleted successfully.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to delete user.");
        }
        return response;
    }
}
