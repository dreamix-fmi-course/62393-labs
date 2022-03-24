package lab4.task1.model;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class User {
    private UUID id;
    private String userName;
    private String email;

    public User(String userName, String email) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email);
    }
}