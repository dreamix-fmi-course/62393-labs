package com.tickets.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class UserDto {
    private UUID id;
    private String username;
    private String email;

    public UserDto(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
