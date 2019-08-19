package com.example.demo.logic;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.HibernateUtil;
import com.example.demo.model.LoginSession;
import com.example.demo.model.Registration;
import com.example.demo.scope.AbstractClass;
import com.example.demo.scope.SHASecure;
import com.example.demo.scope.StrongAES;

public class RegLogic {

	public static boolean regNewUser(Registration reg) {
		Session session = null;
		Transaction transaction = null;
		boolean bol = false;
		reg.setPswd(StrongAES.run(reg.getPswd()));
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			transaction = session.beginTransaction();

			System.out.println(session.isConnected());
			bol = (Integer) session.save(reg) > 0 ? true : false;

				transaction.commit();


			bol = true;
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			System.out.println(e.toString()+"  "+reg);
		} finally {
			if (session != null)
				session.close();
		}
		return bol;
	}

	public static boolean loginUser(HttpServletRequest request,@RequestParam MultiValueMap body) {
		boolean bol = false;
		try {
			String username = body.getFirst("username").toString();
			
			String pswd = SHASecure.get_SHA_1_SecurePassword(body.getFirst("pswd").toString()) ;

			if ((username.length() > 0 & username != null) & (pswd.length() > 0 & pswd != null)) {
				String query = "SELECT username,uid,name,email,authorize FROM registration " + "where pswd='"
						+ pswd + "' and (email='" + username + "' or username='" + username + "')";
				
				System.out.println(query);
				
				
				
				
				

//				List<Object[]> list = AbstractClass.listObj(query);
//				System.out.println("list size "+list.size());
//				if (list != null & list.size() > 0) {
//					for (int i = 0; i < list.size(); i++) {
//						LoginSession ls = new LoginSession();
//						Object[] row = (Object[]) list.get(i);
//						int num = row.length;
//						for (int j = num - 1; j < row.length; j++) {
//							ls.setAuthorize(Integer.parseInt(row[j].toString()));
//							ls.setEmail(row[j - 1].toString());
//							ls.setName(row[j - 2].toString());
//							ls.setId(Integer.parseInt((row[j - 3].toString())));
//							ls.setUsername(row[j - 4].toString());
//							bol = true;
//							request.getSession().setAttribute("loginSession", ls);
//						}
//					}
//				}

				// int num = ((Long) session.createQuery("SELECT
				// secret,username,uid,name,email,authorize FROM registration
				// where pswd='" + pswd
				// + "' and (email='" + username + "' or username='" + username
				// + "')").uniqueResult()).intValue();
				// if (num > 0)
				// request.getSession().setAttribute("admin", rendomNumber());
				// bol = num > 0 ? true : false;
			}
		} catch (Exception e) {
		} finally {

		}
		return bol;
	}
	public static Registration getProductUserDetail(int userid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			Transaction transaction  = session.beginTransaction();
			return (Registration) session.get(Registration.class, userid);
		} catch (Exception e) {
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}
	
	public static boolean checkLogin(HttpServletRequest request){
		try {
			if(request.getSession().getAttribute("loginSession")!=null){
				LoginSession ls=(LoginSession)request.getSession().getAttribute("loginSession");
				System.out.println("loginSession: "+ls);
				if(ls.getId()>0)
					return false;
			}
		} catch (Exception e) {
		}
		return true;
	}

	private static int rendomNumber() {
		Random rn = new Random();
		int range = 99999999;
		return rn.nextInt(range);
	}

	public static void main(String[] args) {

	}

}
