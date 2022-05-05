package com.tickets.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@ToString
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;

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
}
