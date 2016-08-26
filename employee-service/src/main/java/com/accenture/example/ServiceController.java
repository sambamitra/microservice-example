package com.accenture.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World!";
	}
}
