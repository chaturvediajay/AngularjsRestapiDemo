package com.example.demo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.demo.model.HibernateUtil;
import com.example.demo.model.LoginSession;
import com.example.demo.model.Product;
import com.example.demo.model.ProductDetail;
import com.example.demo.nonModel.SlideHome;
import com.example.demo.scope.AbstractClass;
import com.example.demo.scope.StrongAES;

public class ProductLogic {
	

	public static boolean uniqueSingleClass(HashMap<String, String> myMap, Object obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(obj.getClass());
			for (Entry<String, String> entry : myMap.entrySet())
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			obj = (Object) criteria.uniqueResult();
			System.out.println("obj " + obj);
			if (obj != null)
				return false;
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean product_registration(Product product) {
		boolean bol = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			product.setPkey(StrongAES.stringIntGen(10, 2));
			bol = (Integer) session.save(product) > 0 ? true : false;
			transaction.commit();
			bol = true;
		} catch (Exception e) {
		
			if (transaction != null)
				transaction.rollback();
			System.out.println(e.toString());
		} finally {
			if (session != null)
				session.close();
		}
		return bol;
	}

	public static Product getByPkey(HttpServletRequest request) {
		Product pro = null;
		String pkey = request.getParameter("pkey").toString();
		String scope = request.getParameter("scope").toString();
		System.out.println("pkey:- " + pkey + " scope:- " + scope + "/" + request.getSession().getAttribute("rand"));
		System.out.println((request.getSession().getAttribute("rand").toString()).equals(scope) + "   " + pkey);
		if ((request.getSession().getAttribute("rand").toString()).equals(scope) & pkey != null) {
			String query = "SELECT p.title" + ",(Select menu from menu1 where m1id=p.m1id) as m1"
					+ ",(Select submenu from menu2 where m2id=p.m2id) as m2"
					+ ",(Select submenu from menu3 where m3id=p.m3id) as m3" + " FROM product p" + " where p.pkey='"
					+ pkey + "'";

			System.out.println(query);

			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();

				List<Object[]> list = AbstractClass.listObj(query);
				for (int i = 0; i < list.size(); i++) {
					Object[] row = (Object[]) list.get(i);
					for (int j = row.length - 1; j < row.length; j++) {
						pro = new Product();
						pro.settM3(row[j].toString());
						pro.settM2(row[j - 1].toString());
						pro.settM1(row[j - 2].toString());
						pro.setTitle(row[j - 3].toString());
						pro.setPkey("pkey");
					}
				}
				// Criteria criteria = session.createCriteria(Product.class);
				// System.out.println(3);
				// System.out.println(4);
				// pro = (Product) criteria.uniqueResult();
			} catch (Exception e) {
			} finally {
				if (session != null)
					session.close();
			}
		}

		return pro;
	}

	public static boolean addProduct(HttpServletRequest request) {
		boolean bol = false;
		try {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			Transaction transaction = null;
			ProductDetail pd = new ProductDetail();
			pd.setPkey(request.getParameter("pkey"));
			pd.setSize(request.getParameter("size"));
			pd.setColor(request.getParameter("color"));
			pd.setMrp(request.getParameter("mrp"));
			pd.setSmrp(request.getParameter("smrp"));
			pd.setCount(Integer.parseInt(request.getParameter("qty")));
			pd.setDescription(request.getParameter("description"));
			pd.setUid(ls.getId());
			pd.setUrl("test");
			System.out.println("product detail " + pd);
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			bol = (Integer) session.save(pd) > 0 ? true : false;
			transaction.commit();
		} catch (Exception e) {
		}
		return bol;
	}

	public static List<SlideHome> getProductHome(String queryS, String pkey) {
		List<SlideHome> lsh = new ArrayList<SlideHome>();
		String query = null;

		if (queryS.equals("all"))
			query = "SELECT group_concat(concat(`size`) separator ',') as size, pd.description, p.title,pd.mrp,pd.smrp,pd.pkey FROM product p"
					+ " join product_detail pd on   pd.pkey=p.pkey group by p.pkey";
		else if (queryS.equals("single"))
			query = "SELECT " + "pd.description,group_concat(concat(`size`) separator ',') as size,"
					+ " p.title,pd.mrp,pd.smrp,pd.pkey" + " FROM product p"
					+ " join product_detail pd on pd.pkey=p.pkey " + " where p.pkey='" + pkey + "' "
					+ "group by p.pkey";

		System.out.println(query);

		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			List<Object[]> list = AbstractClass.listObj(query);
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					SlideHome sh = new SlideHome();
					sh.setPkey(row[j].toString());
					sh.setSmrp(row[j - 1].toString());
					sh.setMrp(row[j - 2].toString());
					sh.setTitle(row[j - 3].toString());
					sh.setSize(row[j - 4].toString());
					sh.setDescription(row[j - 5].toString());
					lsh.add(sh);
				}
			}
			// Criteria criteria = session.createCriteria(Product.class);
			// System.out.println(3);
			// System.out.println(4);
			// pro = (Product) criteria.uniqueResult();
		} catch (Exception e) {
		} finally {
			if (session != null)
				session.close();
		}

		return lsh;
	}

	public static List<ProductDetail> singleProduct(String pkey) {
		List<ProductDetail> pd = null;
		String query = "SELECT  p.title,pd.mrp,pd.smrp,pd.pkey,pd.description,"
				+ "group_concat(concat(`size`) separator ',') as size" + " FROM product p"
				+ " join product_detail pd on   pd.pkey=p.pkey group by p.pkey";
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(ProductDetail.class);
			criteria.add(Restrictions.eq("pkey", pkey));
			pd = criteria.list();
			session.close();
		} catch (Exception e) {

		}

		return pd;
	}

	public static List<ProductDetail> getColorList(String pKey, String size) {
		String str = "";
		List list = null;
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			try {
				// pp3355241260
				tx = session.beginTransaction();
				list = new ArrayList<>();
				list = session.createQuery("From ProductDetail where pkey='" + pKey + "' and size='" + size
						+ "' and status='0' and visible='0'").list();
			} catch (Exception e) {
				if (tx != null) {
					session.getTransaction().rollback();
				}
				if (session != null) {
					session.getTransaction().commit();
					session.close();
				}
			}
		} finally {
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(getColorList("pp3355241260", "x").size());
	}

}
