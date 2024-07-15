package com.pharmaceutical.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing users in the system.
 */
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique identifier for the user

    @Column(name = "username", nullable = false)
    private String username;  // Username of the user, cannot be null

    @Column(name = "password", nullable = false)
    private String password;  // Password of the user, cannot be null

    @Column(name = "email", nullable = false)
    private String email;  // Email of the user, cannot be null

    @Column(name = "role_id", nullable = false)
    private Long roleId;  // Role ID of the user, linked to roles table, cannot be null

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;  // Indicates if the user account is active or not, cannot be null

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    // Override toString() method to provide a string representation of the object

    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", roleId=" + roleId + ", isActive=" + isActive + "]";
    }
}
