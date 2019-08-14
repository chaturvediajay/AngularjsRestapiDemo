package com.example.demo.logic;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.demo.cart.PaymentDetails;
import com.example.demo.model.HibernateUtil;
public class PaymentLogic {
	public static boolean trasactionUpdate(String orderId, String trasactionid, String json, int con) {
		boolean bol = false;
		try {
			Transaction tx = null;
			Session session = null;
			PaymentDetails pd;
			try {
				pd = new PaymentDetails();
				session = HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();
				String query = "UPDATE payment_details SET gateWayCode='" + json + "', status=" + con
						+ " WHERE orderId=" + orderId + " and txid=" + trasactionid + "";
				System.out.println("query");
				bol = session.createSQLQuery(query).executeUpdate() > 0 ? true : false;
				session.getTransaction().commit();
			} catch (Exception e) {
				
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		} catch (HibernateException e) {

		}
		return bol;
	}

}
