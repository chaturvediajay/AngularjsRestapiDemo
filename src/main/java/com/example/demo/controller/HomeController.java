package com.example.demo.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ComponentScan(basePackages = { "com.example.demo.controller" })
public class HomeController {

	@GetMapping(value = "/")
	public String getIndex() {
		return "index";
	}

}
