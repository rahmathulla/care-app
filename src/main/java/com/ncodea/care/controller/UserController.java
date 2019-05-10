package com.ncodea.care.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.ncodea.care.dto.UserDto;
import com.ncodea.care.entity.User;
import com.ncodea.care.exception.UserAlreadyRegisteredException;
import com.ncodea.care.exception.UserNotFoundException;
import com.ncodea.care.service.UserService;
import com.ncodea.care.util.CareAppUrls;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/care/user")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value= CareAppUrls.LOGIN_USER, method= RequestMethod.GET)
	public ResponseEntity<User> loginUser(@RequestHeader("Authorization") String authorizationHeader) throws UserNotFoundException {
		 System.out.println("===LOGIN_USER ======");
		 String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		 System.out.println("===sessionId ======"+sessionId);
		User user = userService.loginUser(authorizationHeader);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@RequestMapping(value= CareAppUrls.REGISTER_USER, method= RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) throws UserAlreadyRegisteredException {
		 System.out.println("===REGISTER_USER ======");
		 String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		 System.out.println("===sessionId ======"+sessionId);
		userService.registerUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@RequestMapping(value= CareAppUrls.UPDATE_USER, method= RequestMethod.GET)
	public ResponseEntity<String> updateUser() throws UserNotFoundException {
		 System.out.println("===UPDATE_USER ======");
		 String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		 System.out.println("===sessionId ======"+sessionId);
	
		return ResponseEntity.status(HttpStatus.OK).body("updted");
	}
}
