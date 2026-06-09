package com.java.ServiceReq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ServiceReq.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	public List<Ticket> findByTitleContainingIgnoreCase(String title);
}
