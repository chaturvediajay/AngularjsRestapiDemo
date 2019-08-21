package com.example.demo.logic;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.demo.model.Menu1;

public class MenuLogic {
	private String m1id;
	private String m2id;
	private String categories_val;
	private String categories_key;
	private String m3id;
	private String cate;

	public MenuLogic(HttpServletRequest request) {
		if (request.getParameter("m1id") != null)
			this.m1id = request.getParameter("m1id").trim();
		if (request.getParameter("m2id") != null)
			this.m2id = request.getParameter("m2id").trim();
		if (request.getParameter("categories_val") != null)
			this.categories_val = request.getParameter("categories_val").trim();
		if (request.getParameter("categories_key") != null)
			this.categories_key = request.getParameter("categories_key").trim();
		if (request.getParameter("m3id") != null)
			this.m3id = request.getParameter("m3id").trim();
		if (request.getParameter("cate") != null)
			this.cate = request.getParameter("cate").trim();
	}

	public boolean addCategories() {
		String res = "";
		boolean bol = false;
	//	if (((this.m1id != null) ? 1 : 0) & ((this.m2id != null) ? 1 : 0) & ((this.categories_val != null) ? 1 : 0)
		//		& ((this.categories_key != null) ? 1 : 0) & ((this.m3id != null) ? 1 : 0)) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("m1id", this.m1id);
				obj.put("m2id", this.m2id);
				obj.put("categories_val", this.categories_val);
				obj.put("m3id", this.m3id);
				obj.put("categories_key", this.categories_key);
				obj.put("status", true);

				Session session = null;
				Transaction transaction = null;
				try {


					if (this.cate.equals("txtMenu1")) {
						Menu1 m = new Menu1();
						m.setMenu(this.categories_val);
						bol = (((Integer) session.save(m)).intValue() > 0);
					} else if (this.cate.equals("optMenu2")) {
//						Menu2 m2 = new Menu2();
//						Menu1 m1 = new Menu1();
//						m1.setM1id(Integer.parseInt(this.m1id));
//						m2.setSubmenu(this.categories_val);
//						m2.setM1id(m1);
//						bol = (((Integer) session.save(m2)).intValue() > 0);
					} else if (this.cate.equals("optMenu3")) {
//						Menu3 m3 = new Menu3();
//						Menu2 m2 = new Menu2();
//						Menu1 m1 = new Menu1();
//						m1.setM1id(Integer.parseInt(this.m1id));
//						m2.setM2id(Integer.parseInt(this.m2id));
//						m3.setSubmenu(this.categories_val);
//						m3.setM1id(m1);
//						m3.setM2id(m2);
//						bol = (((Integer) session.save(m3)).intValue() > 0);
					}

//					if (!transaction.wasCommitted()) {
//						transaction.commit();
//						bol = true;
//					}

				} catch (Exception e) {
					if (transaction != null)
						transaction.rollback();
				} finally {
					if (session != null) {
						session.close();
					}
				}
				res = obj.toString();
			} catch (JSONException e) {

				res = "{\"msg\":\"" + e.toString() + "\",\"status\":" + Character.MIN_VALUE + ",\"deep\":" + "\"" + "no"
						+ "\"" + "}";
			}
		//}
		return bol;
	}

	public static List<?> retriveMenu(JSONObject obj) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
		List<?> lstmenu = null;

		try {
			if (obj.getInt("id") > 0) {
//				session.beginTransaction();
//				if (obj.get("select").equals("menu1")) {
//					lstmenu = session.createQuery("from Menu2 where  m1id=" + obj.getInt("id")).list();
//				} else if (obj.get("select").equals("menu2")) {
//					lstmenu = session.createQuery("from Menu3 where  m1id=" + obj.getInt("id")).list();
//				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
//			if (session != null) {
//				session.close();
//			}
		}
		return lstmenu;
	}

	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//
//		session.close();
	}

	public static List<Menu1> getMenuObj() {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
		List<Menu1> menul = null;
		try {
//			Criteria criteria = session.createCriteria(Menu1.class);
//			menul = criteria.list();
//			session.getTransaction().commit();
		} catch (HibernateException e) {
//			session.getTransaction().rollback();
		} finally {

//			if (session != null)
//				session.close();
		}
		return menul;
	}

	public String toString() {
		return "MenuLogic [m1id=" + this.m1id + ", m2id=" + this.m2id + ", categories_val=" + this.categories_val
				+ ", categories_key=" + this.categories_key + ", m3id=" + this.m3id + "]";
	}

	private boolean checkBoolean(HttpServletRequest request, String key) {
		if (request.getParameter(key) != null)
			return true;
		return false;
	}

	public String getM1id() {
		return this.m1id;
	}

	public void setM1id(String m1id) {
		this.m1id = m1id;
	}

	public String getM2id() {
		return this.m2id;
	}

	public void setM2id(String m2id) {
		this.m2id = m2id;
	}

	public String getM3id() {
		return this.m3id;
	}

	public void setM3id(String m3id) {
		this.m3id = m3id;
	}

	public String getCategories_val() {
		return this.categories_val;
	}

	public void setCategories_val(String categories_val) {
		this.categories_val = categories_val;
	}

	public String getCategories_key() {
		return this.categories_key;
	}

	public void setCategories_key(String categories_key) {
		this.categories_key = categories_key;
	}

	public String getCate() {
		return this.cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}
}