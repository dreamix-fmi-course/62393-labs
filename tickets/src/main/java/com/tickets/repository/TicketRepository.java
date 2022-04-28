package com.tickets.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tickets.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, UUID> {}
