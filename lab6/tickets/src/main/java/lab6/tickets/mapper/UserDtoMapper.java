package lab6.tickets.mapper;

import lab6.tickets.dto.UserDto;
import lab6.tickets.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public User convertToEntity(UserDto userDto) {
        if (userDto.getId() != null) {
            return new User(
                    userDto.getId(),
                    userDto.getUserName(),
                    userDto.getEmail()
            );
        }
        return new User(
                userDto.getUserName(),
                userDto.getEmail()
        );
    }

    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }
}
