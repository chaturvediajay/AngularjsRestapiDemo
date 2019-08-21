package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.logic.MenuLogic;
import com.example.demo.model.Menu1;
import com.example.demo.service.IMenu1Service;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
	
	@Autowired
	private IMenu1Service menu1Service;
	
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET })
	public String indexGet(HttpServletRequest request, ModelMap map) {
		if (request.getSession().getAttribute("user") != null)
			return "admin/index";
		else
			return "redirect:/login";
	}

	@RequestMapping(value = { "/add_menu" }, method = { RequestMethod.GET })
	public String getdd_menu(HttpServletRequest request, ModelMap model) {
		if (request.getSession().getAttribute("user") != null) {
			List<Menu1> m1 =menu1Service.getAllRegistrations();
			System.out.println("******* "+m1.size());
			
			for(Menu1 mw1:m1) {
				System.out.println(mw1);
			}
			model.addAttribute("menu", m1);
			return "admin/add_menu";
			
		}
		else
			return "redirect:/login";
	}

	@RequestMapping(value = { "/menuEntry" }, method = { RequestMethod.GET })
	public String getForms(HttpServletRequest request, ModelMap model) {
		if (request.getSession().getAttribute("user") == null)
			return "redirect:/";
		model.addAttribute("menu", MenuLogic.getMenuObj());
		return "admin/menu_add";
	}
}
