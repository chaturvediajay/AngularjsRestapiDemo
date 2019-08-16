package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Registration;
import com.example.demo.service.IRegistrationService;

@Controller
@RequestMapping("user")
public class RestControllerDemo {
	
	@Autowired
	private IRegistrationService articleService;
	
	@GetMapping("articles")
	public ResponseEntity<List<Registration>> getAllArticles() {
		List<Registration> list = articleService.getAllRegistrations();
		return new ResponseEntity<List<Registration>>(list, HttpStatus.OK);
	}
}
