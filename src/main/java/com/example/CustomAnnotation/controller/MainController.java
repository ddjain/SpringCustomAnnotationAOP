package com.example.CustomAnnotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.CustomAnnotation.annotation.CustomAnnotation;

@RestController
public class MainController   {
	@CustomAnnotation(debug=true)
	@GetMapping("/healthcheck")
	public String helloUser() {
		return "OK";
	}
}
