package com.tickets.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.model.Performer;

@Repository
public interface PerformerRepository extends CrudRepository<Performer, UUID> {
    List<Performer> findAll();
}
