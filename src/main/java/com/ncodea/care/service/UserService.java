package com.ncodea.care.service;

import com.ncodea.care.dto.UserDto;
import com.ncodea.care.entity.User;
import com.ncodea.care.exception.UserAlreadyRegisteredException;
import com.ncodea.care.exception.UserNotFoundException;

public interface UserService {

	public void registerUser(UserDto userDto) throws UserAlreadyRegisteredException;
	public User loginUser(String authorizationHeader) throws UserNotFoundException;
}
