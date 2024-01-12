package com.springAngularConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springAngularConnect.pojo.User;
import com.springAngularConnect.repo.UserRepo;

@RestController
@CrossOrigin(origins="*")//for all external networks we can use hitting this requests
public class UserController {
	@Autowired
	UserRepo repo;
	
	
	//insert
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		repo.save(user);
		return "Hi "+user.getName()+" is registered successfully...!";
		
	}
	
	
	//list of users 
	@GetMapping("/getAllusers")
	public List<User> findAllusers(){
		return repo.findAll();
	}
	
	
	//delete by id
	
	@DeleteMapping("/cancel/{id}")
	public List<User> cancelregistration(@PathVariable int id){
		repo.deleteById(id);
		return repo.findAll();
	}
	
	
	//search via email 
	@GetMapping("/findbyemail/{email}")
	public List<User> findUser(@PathVariable String email){
		return repo.findByemail(email);
	}
	
	// update by id
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User existingUser = repo.findById(id).orElse(null);

        if (existingUser != null) {
            // Update the fields of the existing user with the values from the updatedUser
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setExperience(updatedUser.getExperience());
            // Add more fields to update as needed

            repo.save(existingUser);
            return "User with id " + id + " updated successfully!";
        } else {
            return "User with id " + id + " not found.";
        }
    }

}
