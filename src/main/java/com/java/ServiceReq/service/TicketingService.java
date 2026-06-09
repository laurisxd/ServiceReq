package com.java.ServiceReq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ServiceReq.entity.Ticket;
import com.java.ServiceReq.repository.TicketRepository;

@Service
public class TicketingService {

	@Autowired
	TicketRepository ticketrep;
	
	// CRUD Methods

	// Reading data from the table.
	public List<Ticket> getAllTickets(){
		return ticketrep.findAll();
	}
	
	// Deleting data from the table by id.
	public void deleteTicket(int id) {
		ticketrep.deleteById(id);
	}
	
	// Saving the data to the table.
	public void addTicket(Ticket ticket) {
		ticketrep.save(ticket);
	}
	
	// Updating the data to the table.
	public Ticket findTicket(int id) {
		return ticketrep.findById(id).orElse(null);
	}
	// Searching data by the title name.
	public List<Ticket> searchTicket(String title) {
		return ticketrep.findByTitleContainingIgnoreCase(title);
	}
	
}

