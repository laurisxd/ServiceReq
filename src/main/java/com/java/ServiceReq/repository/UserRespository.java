package com.java.ServiceReq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.ServiceReq.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
