package com.ncodea.care.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ncodea.care.entity.User;

import lombok.Data;

//@Data
public class UserDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private boolean careProfessional;
	
	public User toUser() {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		
		return user;
		
	}

}
