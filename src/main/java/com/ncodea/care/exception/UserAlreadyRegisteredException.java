package com.ncodea.care.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason= "User is already registered")
public class UserAlreadyRegisteredException extends Exception {

	private static final long serialVersionUID = 1L;

}
