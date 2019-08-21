package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IRegistrationDAO;
import com.example.demo.model.Menu1;
import com.example.demo.model.Menu2;
import com.example.demo.model.Menu3;
import com.example.demo.model.Registration;

@Transactional
@Repository
public class MenuService implements IMenu1Service {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Menu1> getAllRegistrations() {
		String hql = "FROM Menu1";
		return (List<Menu1>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Menu1 getMenu1ById(int menu1Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu1 createMenu1(Menu1 menu1) {
		entityManager.persist(menu1);

		return menu1;
	}

	@Override
	public void updateRegistration(Menu1 menu1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menu1Registration(int menu1Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Menu1 menu1Exists(String username, String pswd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu2 createMenu2(Menu2 menu2) {
		entityManager.persist(menu2);
		return menu2;
	}

	@Override
	public Menu3 createMenu3(Menu3 menu3) {
		entityManager.persist(menu3);
		return menu3;
	}

	@Override
	public List<?> retriveMenu(JSONObject obj) {
		List<?> lstmenu = null;

		try {
			if (obj.getInt("id") > 0) {
				if (obj.get("select").equals("menu1")) {
					lstmenu=(List<Menu1>) entityManager.createQuery("from Menu2 where  m1id=" + obj.getInt("id")).getResultList();
					
				} else if (obj.get("select").equals("menu2")) {
					
					lstmenu=(List<Menu1>) entityManager.createQuery("from Menu3 where  m1id=" + obj.getInt("id")).getResultList();
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return lstmenu;
	}

}
