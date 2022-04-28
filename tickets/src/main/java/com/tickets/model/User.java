package com.tickets.model;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name="username", nullable=false, length=16)
    private String username;

    @Column(name = "email", nullable=false, length=64, unique=true)
    private String email;

    public User() {}

    public User(String username, String email) {
        this(UUID.randomUUID(), username, email);
    }

    public User(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    @PrePersist
    private void onCreate() {
        this.setId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
