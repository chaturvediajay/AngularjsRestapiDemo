package com.example.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.MenuLogic;
import com.example.demo.model.Menu1;
import com.example.demo.model.Menu2;
import com.example.demo.model.Menu3;
import com.example.demo.service.IMenu1Service;
import com.google.gson.Gson;

@Controller
public class MenuController {
	@Autowired
	private IMenu1Service menu1Service;

	@GetMapping("menu1")
	public ResponseEntity<List<Menu1>> getAllArticles() {
		List<Menu1> list = menu1Service.getAllRegistrations();
		return new ResponseEntity<List<Menu1>>(list, HttpStatus.OK);
	}

	@PostMapping("addMenus")
	public ResponseEntity<?> postOrderDetail(HttpServletResponse response, HttpSession session,
			HttpServletRequest request) {
		String json1 = "";
		Menu1 menu1 = new Menu1();
		Menu2 menu2 = new Menu2();
		Menu3 menu3 = new Menu3();
		JSONObject obj = new JSONObject();
        String out="";
		MenuLogic ml = new MenuLogic(request);
		try {

			String req_categories_val = request.getParameter("categories_val");
			if (req_categories_val!=null) {

				String req_cate = request.getParameter("categories_key");
				int req_m1id = Integer.parseInt(request.getParameter("m1id"));
				if (req_cate.equals("optMenu1")) {
					menu1.setMenu(req_categories_val);
					menu1 = menu1Service.createMenu1(menu1);
					out=menu1.toString();
				}
				else if (req_cate.equals("optMenu2")) {
					menu2.setM1id(req_m1id);
					menu2.setSubmenu(req_categories_val);
					menu2 = menu1Service.createMenu2(menu2);
					out=menu2.toString();
				}
				else if (req_cate.equals("optMenu3")) {
				//	../addMenus?m1id=1&m2id=4&categories_val=m23&categories_key=optMenu3&m3id=undefined&cate=optMenu3	
					int req_m2id = Integer.parseInt(request.getParameter("m2id"));
					menu3.setM2id(req_m2id);
					menu3.setM1id(req_m1id);
					menu3.setSubmenu(req_categories_val);
					
					menu3 = menu1Service.createMenu3(menu3);
					out=menu3.toString();
				}
					
			}
			json1 = "{\"msg\":\"data insert sucessfull\",\"status\":" + out + ",\"deep\":"
					+ "\"" + "sucess" + "\"" + "}";
		} catch (Exception e) {
			System.out.println(e.toString());

			json1 = "{\"msg\":\"" + e.toString() + "\",\"status\":" + Character.MIN_VALUE + ",\"deep\":" + "\""
					+ obj.toString() + "\"" + "}";
		}
		return new ResponseEntity(json1, HttpStatus.OK);

	}
	
	@PostMapping("retrive_menu")
	public void postRetriveMenu(@RequestParam("json") String json, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) throws Exception {
		List<?> lst = null;
		System.out.println(json);
		System.out.println("retrive_menu");
		JSONObject obj = new JSONObject(json);
		try {
			String menu = "";
			if (obj.get("select").toString().length() > 0) {
				menu = obj.get("select").toString();
				if (menu.equals("menu1")) {
					lst = menu1Service.retriveMenu(obj);
				} else if (menu.equals("menu2")) {
					lst = menu1Service.retriveMenu(obj);
					
				}
			}

			obj.put("retrive", (new Gson()).toJson(lst));
			json = obj.toString();
		} catch (Exception e) {

			System.out.println(e.toString());

			json = "{\"msg\":\"" + e.toString() + "\",\"status\":" + Character.MIN_VALUE + ",\"deep\":" + "\""
					+ obj.toString() + "\"" + "}";
		}
		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}


}
