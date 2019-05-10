package com.ncodea.care.service.impl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ncodea.care.dto.UserDto;
import com.ncodea.care.entity.User;
import com.ncodea.care.exception.UserAlreadyRegisteredException;
import com.ncodea.care.exception.UserNotFoundException;
import com.ncodea.care.repository.UserRepository;
import com.ncodea.care.service.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void registerUser(UserDto userDto) throws UserAlreadyRegisteredException {
		User retrievedUser = userRepository.findByEmail(userDto.getEmail());
	    if(retrievedUser == null) {
			userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			User user = userDto.toUser();
			User savedUser = userRepository.save(user);
			System.out.println("=========savedUser id========"+savedUser.getUserId());
	    } else {
	    	throw new UserAlreadyRegisteredException();
	    }
	}
	
	@Override
	public User loginUser(String authorizationHeader) throws UserNotFoundException {
		
		String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
		byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
	    String credentials = new String(credDecoded);
	    // credentials = username:password
	    final String[] values = credentials.split(":", 2);
	    
	    String email = values[0];
	    String password = values[1];
	    
	    User retrievedUser = userRepository.findByEmail(email);
	    if(retrievedUser != null) {
	    	System.out.println("=========retrieved user id========"+retrievedUser.getUserId());
		    boolean isUSerFound = bCryptPasswordEncoder.matches(password, retrievedUser.getPassword());
		    if(isUSerFound) {
		    	return retrievedUser;
		    }
	    }
	    System.out.println("===No User ======");
	    throw new UserNotFoundException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
