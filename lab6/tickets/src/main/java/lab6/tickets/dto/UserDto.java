package lab6.tickets.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {
    private UUID id;
    private String userName;
    private String email;

    public UserDto(UUID id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
