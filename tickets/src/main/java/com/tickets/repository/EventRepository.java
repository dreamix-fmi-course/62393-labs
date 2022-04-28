package com.tickets.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {}
