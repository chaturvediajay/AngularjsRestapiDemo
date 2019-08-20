package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String indexGet(HttpServletRequest request, ModelMap map) {
		if (request.getSession().getAttribute("user") != null)
			return "admin/index";
		else
			return "redirect:/login";
	}
}
