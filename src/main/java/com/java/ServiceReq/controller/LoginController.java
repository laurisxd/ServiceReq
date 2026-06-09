package com.java.ServiceReq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.ServiceReq.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

	@Autowired
	UserService userserv;
	
	@GetMapping("/")
	public String loginHomepage() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginhome() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerhome() {
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		
		if(userserv.checkLogin(username, password)) {
			session.setAttribute("loggedinuser", username);
			return "redirect:/home";
		} else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password) {
		if(userserv.registerUser(username, password)) {
			return "redirect:/login";
		} else {
			return "redirect:/register";
		}
	}	
	

}
