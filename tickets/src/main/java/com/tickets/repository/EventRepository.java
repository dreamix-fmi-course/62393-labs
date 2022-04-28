package com.tickets.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID> {
    List<Event> findAll();

    @Query(value = "SELECT * FROM events\n" +
            "WHERE date > ?1 AND date < ?2", nativeQuery = true)
    List<Event> findAllBetween(LocalDate from, LocalDate to);
}
