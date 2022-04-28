package com.tickets.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findAll();
}
