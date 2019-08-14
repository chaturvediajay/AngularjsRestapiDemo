package com.example.demo.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.demo.model.HibernateUtil;
import com.example.demo.model.Menu1;
import com.example.demo.model.Menu2;
import com.example.demo.model.Menu3;

public class MenuLogic {

	public static List<Menu1> getMenuObj(Object obj, int num) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Menu1> menul=null;
		System.out.println("  "+obj+"  "+num);
		try {
			Criteria criteria = session.createCriteria(Menu1.class);
			menul = criteria.list();
//			for(Menu1 m1:menul)
//				System.out.println(" Menu Name= "+m1.getMenu());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		return menul;
	}
	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		System.out.println(session.isConnected());
//		session.close();
		
		MenuLogic.getMenuObj((Object) new Menu1(), 0);
	}
	
	
	
	
	public static String addMenus(int m1id, int m2id, String title, String categories) {
		String str;
		str = "";
		Session session = null;
		Transaction transaction = null;
		try {
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				if (categories.equals("menu1")) {
					if (MenuLogic.uniqueTitle("menu1", session, title)) {
						Menu1 cate = new Menu1();
						cate.setMenu(title);
						str = (Integer) session.save((Object) cate) > 0 ? String.valueOf(str) + "Menu add sucessfully"
								: String.valueOf(str) + "Menu:- please try after some time";
					} else {
						str = String.valueOf(str) + "Menu:- already Menu in list";
					}
				} else if (categories.equals("menu2")) {
					if (MenuLogic.uniqueTitle("menu2", session, title)) {
						Menu2 company = new Menu2();
						company.setSubmenu(title);
						company.setM1id(m1id);
						// System.out.println(title+" "+m1id);
						str = (Integer) session.save((Object) company) > 0
								? String.valueOf(str) + "SubMenu add sucessfully"
								: String.valueOf(str) + "SubMenu:- please try after some time";
					} else {
						str = String.valueOf(str) + "SubMenu:- already SubMenu in list";
					}
				} else if (categories.equals("menu3")) {
					if (MenuLogic.uniqueTitle("menu3", session, title)) {
						Menu3 model = new Menu3();
						model.setSubmenu(title);
						model.setM2id(m2id);
						str = (Integer) session.save((Object) model) > 0
								? String.valueOf(str) + "Sub-SubMenu add sucessfully"
								: String.valueOf(str) + "Sub-SubMenu:- please try after some time";
					} else {
						str = String.valueOf(str) + "Sub-SubMenu:- already Sub-SubMenu in list";
					}
				}
				
			} catch (Exception ex) {
				str = String.valueOf(str) + "categories add exception:- " + ex.toString();
				if (transaction != null) {
					transaction.rollback();
				}
				if (session != null) {
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return str;
	}
	public static Map<String, String> getMenu2Bym1id(int id, String cateName) {
		LinkedHashMap<String, String> options;
		options = new LinkedHashMap<String, String>();
		try {
			try {
				HashMap<String, Integer> hm = new HashMap<>();
				if (cateName.equals("menu1")) {
					hm.put("m1id", id);
					List<Menu2> menu2 = (List<Menu2>) ((Object) MenuLogic.getMenuObjCrtial(new Menu2(), hm));
					for (Menu2 cat : menu2) {
						options.put("" + cat.getM2id(), cat.getSubmenu());
					}

				} else if (cateName.equals("menu2")) {
					hm.put("m2id", id);
					List<Menu3> menu3 = (List<Menu3>) ((Object) MenuLogic.getMenuObjCrtial(new Menu3(), hm));
					for (Menu3 cat : menu3) {
						options.put("" + cat.getM3id(), cat.getSubmenu());
					}
				}
				options.put("0", "Select");
			} catch (Exception ex) {
				System.out.println("getMenu2Bym1id(com.menu) :- " + ex.toString());
			}
		} finally {
		}
		return options;
	}
	public static List<Object> getMenuObjCrtial(Object obj, HashMap<?, ?> hm) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Object> list = Collections.emptyList();
		try {
			Transaction transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Map.Entry<?, ?> entry : hm.entrySet()) {
				criteria.add(Restrictions.eq((String) entry.getKey(), (int) entry.getValue()));
				System.out.println(entry.getKey() + "  " + entry.getValue());
			}
			list = criteria.list();
		} catch (HibernateException e) {
			TestConnection.errorInfoInsert("com.logic.getMenuObjCrtial ", e.toString());
		} finally {
			session.close();
		}
		return list;
	}
	
	private static boolean uniqueTitle(String categories, Session session, String title) {
		String query = "";
		try {
			if (categories.equals("menu1")) {
				query = "SELECT COUNT(*) FROM  Menu1 where menu='" + title + "'";
			} else if (categories.equals("menu2")) {
				query = "SELECT COUNT(*) FROM  Menu2 where submenu='" + title + "'";
			} else if (categories.equals("menu3")) {
				query = "SELECT COUNT(*) FROM  Menu3 where submenu='" + title + "'";
			}
			if ((Long) session.createQuery(query).uniqueResult() > 0) {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("uniqure title: " + ex.toString());
		}
		return true;
	}

}
