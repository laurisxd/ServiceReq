package com.java.ServiceReq.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ServiceReq.entity.User;
import com.java.ServiceReq.repository.UserRespository;

@Service
public class UserService {

	@Autowired
	UserRespository userrep;
	
	public boolean checkLogin(String username, String password) {
		
		User user = userrep.findByUsername(username);
		
		return user != null && user.getPassword().equals(password);
	}
	
	public boolean registerUser(String username, String password) {
		User user = userrep.findByUsername(username);
		
		if(user != null) {
			return false;
		}
		
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		
		userrep.save(newUser);
		return true;
	}
}
