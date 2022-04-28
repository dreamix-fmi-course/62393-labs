package com.tickets.mapper;

import com.tickets.dto.UserDto;
import com.tickets.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public User convertToEntity(UserDto userDto) {
        if (userDto.getId() != null) {
            return new User(
                    userDto.getId(),
                    userDto.getUsername(),
                    userDto.getEmail()
            );
        }
        return new User(
                userDto.getUsername(),
                userDto.getEmail()
        );
    }

    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
