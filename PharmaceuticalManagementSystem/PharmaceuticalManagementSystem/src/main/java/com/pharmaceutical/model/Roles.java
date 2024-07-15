package com.pharmaceutical.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing roles in the application.
 * Maps to the 'roles' table in the database.
 */
@Entity
@Table(name = "roles")
public class Roles {

    /**
     * The unique identifier for the role.
     * This is the primary key of the roles table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     * This field is mandatory and cannot be empty.
     */
    @Column(name = "role_name", nullable = false)
    private String roleName;

    // Getters and setters

    /**
     * Gets the ID of the role.
     *
     * @return the ID of the role
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the role.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the role.
     *
     * @return the name of the role
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role.
     *
     * @param roleName the name to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // toString method

    /**
     * Returns a string representation of the role.
     *
     * @return a string representation of the role
     */
    @Override
    public String toString() {
        return "Roles [id=" + id + ", roleName=" + roleName + "]";
    }
}
