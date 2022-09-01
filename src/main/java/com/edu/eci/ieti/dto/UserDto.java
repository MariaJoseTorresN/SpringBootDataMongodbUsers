package com.edu.eci.ieti.dto;

import java.util.Date;

public class UserDto {
    private String id;
    private String name;
    private String email;
    private String lastname;
    private Date createdAt;

    public UserDto(String id, String name, String email, String lastname, Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
