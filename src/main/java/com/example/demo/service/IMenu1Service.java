package com.example.demo.service;

import java.util.List;

import org.json.JSONObject;

import com.example.demo.model.Menu1;
import com.example.demo.model.Menu2;
import com.example.demo.model.Menu3;

public interface IMenu1Service {
	List<Menu1> getAllRegistrations();

	Menu1 getMenu1ById(int menu1Id);

	Menu1 createMenu1(Menu1 menu1);

	Menu2 createMenu2(Menu2 menu2);

	Menu3 createMenu3(Menu3 menu3);

	void updateRegistration(Menu1 menu1);

	void menu1Registration(int menu1Id);
	
	List<?> retriveMenu(JSONObject obj);

	Menu1 menu1Exists(String username, String pswd);
}
