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
//		try {
//			if (RegLogic.checkLogin(request))
//				return "redirect:/";
//			else
//				map.addAttribute("featuredPro", " Get Controller");
//
//		} catch (Exception e) {
//			logger.error("AdminController error(42)indexGet  " + e.toString());
//		}
		return "admin/index";
	}
}
