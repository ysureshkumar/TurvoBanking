package com.pramati.turvo.banking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home(){
		
		String str = " <center><h3>Welcome to Turvo Management</h3></center>";
		return str;
	}
}
