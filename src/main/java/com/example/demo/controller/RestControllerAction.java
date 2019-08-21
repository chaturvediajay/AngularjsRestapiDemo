package com.example.demo.controller;
import com.example.demo.logic.MenuLogic;
import com.example.demo.model.Menu2;
import com.example.demo.model.Menu3;
import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerAction {
	@RequestMapping(method = { RequestMethod.POST }, value = { "/retrive_menu123" })
	public void postRetriveMenu(@RequestParam("json") String json, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) throws Exception {
		List<?> lst = null;
		System.out.println(json);
		JSONObject obj = new JSONObject(json);
		try {
			String menu = "";
			if (obj.get("select").toString().length() > 0) {
				menu = obj.get("select").toString();
				if (menu.equals("menu1")) {
					lst = MenuLogic.retriveMenu(obj);
					for (Menu2 m2 : (List<Menu2>)lst) {
//						m2.setM1id(null);
					}
				} else if (menu.equals("menu2")) {
					lst = MenuLogic.retriveMenu(obj);
					for (Menu3 m3 :  (List<Menu3>)lst) {
//						m3.setM1id(null);
//						m3.setM2id(null);
					}
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