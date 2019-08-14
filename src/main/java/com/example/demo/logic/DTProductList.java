package com.example.demo.logic;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.example.demo.model.HibernateUtil;
import com.example.demo.model.LoginSession;
import com.example.demo.model.ProductDetail;
import com.example.demo.scope.AbstractClass;
import com.example.demo.scope.SessionUser;

public class DTProductList {
	private int count = 0;

	public List productList(HttpServletRequest request, int start, int length) {
		int status = 0;
		int visible = 0;
		List list = null;
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				String value = request.getParameter(key).toString();
				System.out.println("key " + key + " value:- " + value);
				if (key.equals("p1"))
					status = Integer.parseInt(value);
				else if (key.equals("p2"))
					visible = Integer.parseInt(value);
			}

			LoginSession ls = (LoginSession) SessionUser.getSessionObject2(request, "loginSession");
			String[] query = new String[4];
			query[0] = "SELECT count(*) as rcount, p.pkey,p.title FROM product p";

			System.out.println("dfsfsf   " + query[0]);
			list = AbstractClass.listObj(query[0], start, length);

			System.out.println("size " + list.size());

			outerloop: for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				for (int j = row.length - 1; j < row.length; j++) {
					if (i == 0) {
						count = Integer.parseInt(row[j - 2].toString());
						break outerloop;
					}
				}
			}

		} catch (Exception e) {

		}
		return list;
	}

	public List<ProductDetail> productListWithPkey(HttpServletRequest request, int start, int length) {
		String pkey = null;
		String scope = null;
		List<ProductDetail> ld = null;
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				String value = request.getParameter(key).toString();
				if (key.equals("p1"))
					pkey = value;
				else if (key.equals("p2"))
					scope = value;
			}

			if (pkey != null) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				Criteria criteria = session.createCriteria(ProductDetail.class);
				criteria.add(Restrictions.eq("pkey", pkey));
				count = criteria.list().size();
				ld = criteria.list();
				session.close();

			}
		} catch (Exception e) {

		}
		return ld;
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ProductDetail.class);
		criteria.add(Restrictions.eq("pkey", "pp3355241260"));
		List<ProductDetail> pro = criteria.list();
		for (ProductDetail p : pro)
			System.out.println(p.getDescription());
		session.close();

		// testSelectUsingProperties();
	}

	public static void testSelectUsingProperties() {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ProductDetail.class);
		// get the count
		ScrollableResults results = criteria.scroll();
		results.last();
		int total = results.getRowNumber() + 1;
		results.close();

		// get the actual records to display
		// final Criteria criteria = getCriteria(session);
		criteria.setFirstResult(2);// first record is 2
		criteria.setMaxResults(3);
		List<Object[]> rows = criteria.list();
		for (Object[] row : rows) {
			System.out.println(row[0] + " and " + row[1]);
		}
		System.out.println("Total records is  " + total);
		session.close();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
