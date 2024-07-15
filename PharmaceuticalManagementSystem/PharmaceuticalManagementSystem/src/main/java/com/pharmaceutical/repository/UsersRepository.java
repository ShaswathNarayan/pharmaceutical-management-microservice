package com.pharmaceutical.repository;

import com.pharmaceutical.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Users entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username to search for.
     * @return The Users entity if found, otherwise null.
     */
    Users findByUsername(String username);
}
