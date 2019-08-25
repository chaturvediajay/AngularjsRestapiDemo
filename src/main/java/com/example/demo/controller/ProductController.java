package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.json.JqueryDataTable;
import com.example.demo.logic.DTProductList;
import com.example.demo.model.Product;
import com.example.demo.service.IMenu1Service;
import com.example.demo.service.IProductService;
import com.example.demo.service.ProductService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = { "/admin" })
public class ProductController {
	@Autowired
	private IMenu1Service menu1Service;

	@Autowired
	private IProductService productService;

	@GetMapping(value = "/add_product")
	public String getIndex(HttpServletRequest request, ModelMap model) {
		if (request.getSession().getAttribute("user") != null) {
			model.addAttribute("menu", menu1Service.getAllRegistrations());
			return "admin/add_product";

		} else
			return "redirect:/login";
	}

	@PostMapping(value = "/add_product")
	public String postAddProduct(HttpServletRequest request, @RequestParam MultiValueMap body, ModelMap model) {
		if (request.getSession().getAttribute("user") != null) {
			System.out.println("@PostMapping---add_product");

			productService.createProduct(body, request);

			model.addAttribute("menu", menu1Service.getAllRegistrations());
			return "admin/add_product";

		} else
			return "redirect:/login";
	}

	@GetMapping(value = "/product_list")
	public String getProduct_list(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {

			// model.addAttribute("menu", menu1Service.getAllRegistrations());
			return "admin/product_list";

		} else
			return "redirect:/login";
	}

	@PostMapping(value = "getProductList")
	public void TestPagePost(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("Hello");
		List<Product> list = null;
		DTProductList dt = new DTProductList();
		try {
			JqueryDataTable jt = new JqueryDataTable();
			jt.getRequestData(request);
			String[] keyword = new String[4];
			keyword[0] = String.valueOf(jt.getDraw());
			keyword[1] = jt.getOrder_column();
			keyword[2] = jt.getOrder_dir();
			keyword[3] = jt.getSearch_value();
			list = (List<Product>) productService.retriveProductJson(request, jt.getStart(), jt.getLength());
			if (list == null)
				list = Collections.EMPTY_LIST;
			System.out.println("list size:-  " + list.size());
			jt.getResposeJson(response, dt.getCount(), (new Gson()).toJson(list).toString());
		} catch (Exception e) {
		}
	}
	@GetMapping(value = "/product_description")
	public String getProduct_description(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {

			return "admin/product_description";

		} else
			return "redirect:/login";
	}

	
	
	

}
