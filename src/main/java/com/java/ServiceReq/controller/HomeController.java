package com.java.ServiceReq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.ServiceReq.entity.Ticket;
import com.java.ServiceReq.service.TicketingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	TicketingService ticketserv;
	
	@GetMapping("/home")
	public String home(HttpSession session, Model m) {
		// Security check. 
		String username = (String) session.getAttribute("loggedinuser");
		
		// if user is not logged in, redirects to login page.
		if (username == null) {
			return "redirect:/login";
		}
		// Adds the username model to welcome message.
		m.addAttribute("user", username);
		
		// For showing all read data from service method.
		m.addAttribute("listoftickets", ticketserv.getAllTickets());
		
		return "home";
	}
	
	@GetMapping("/ticketform")
	public String ticketform(Model m) {
		m.addAttribute("ticket", new Ticket());
		return "ticketform";
	}
	
	@GetMapping("/updateTicket/{id}")
	public String updateTicket(@PathVariable int id, Model m) {
		Ticket existingTicket = ticketserv.findTicket(id);
		
		m.addAttribute("ticket", existingTicket);
		return "ticketform";
	}
	
	@GetMapping("/deleteTicket/{id}")
	public String deleteTicket(@PathVariable int id) {
		ticketserv.deleteTicket(id);
	
		return "redirect:/home";
	}

	@PostMapping("/addTicket")
	public String addTicket(@ModelAttribute Ticket ticket, HttpSession session ,Model m) {
		// Gets the logged-in user from the session.
		String username = (String) session.getAttribute("loggedinuser");
		// If user is not logged in, redirects to login page.
		if (username == null) {
			return "redirect:/login";
		}
		
		if (ticket.getId() == 0) {
			ticket.setCreated_by(username);
			ticket.setCreated_at(java.time.LocalDateTime.now());
		}
		
		
		ticketserv.addTicket(ticket);
		
		return "redirect:/home";
	}
	
	@PostMapping("/searchTicket")
	public String searchTicket(@RequestParam String title, Model m) {
		
		m.addAttribute("ticket", new Ticket());
		m.addAttribute("listoftickets", ticketserv.searchTicket(title));
		
		return "home";
	}
}
