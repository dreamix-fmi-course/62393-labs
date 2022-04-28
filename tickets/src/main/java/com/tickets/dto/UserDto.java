package com.tickets.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {
    private UUID id;
    private String username;
    private String email;

    public UserDto(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
