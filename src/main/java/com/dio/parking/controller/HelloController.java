package com.dio.parking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {

	@GetMapping
	public String hello() {
		return "Hello! Welcome to W.F. Cloud Parking!";
	}
	
}