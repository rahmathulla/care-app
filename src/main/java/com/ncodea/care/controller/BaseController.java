package com.ncodea.care.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/care")
public class BaseController {

	@RequestMapping(method= RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	
}
