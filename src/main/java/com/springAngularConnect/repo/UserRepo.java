package com.springAngularConnect.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springAngularConnect.pojo.User;

public interface UserRepo extends JpaRepository<User,Integer> {
	List<User> findByemail(String email);

}
	