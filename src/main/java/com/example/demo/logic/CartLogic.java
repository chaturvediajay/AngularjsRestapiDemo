package com.example.demo.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.demo.model.CartModel;
import com.example.demo.model.HibernateUtil;
import com.example.demo.model.LoginSession;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.Registration;
import com.example.demo.model.ShippingModel;
import com.example.demo.scope.AbstractClass;
import com.example.demo.scope.CartSession;
import com.example.demo.scope.PropertiesFile;
import com.example.demo.scope.SessionUser;

public class CartLogic {
	public static CartModel addCart(HttpServletRequest request) {
		try {
			String scope = request.getParameter("scope");
			if (scope != null) {
				String[] arScope = scope.split("-");
				String query = "select p.pkey,p.title,pd.mrp,pd.smrp,pd.description,pd.status,pd.visible,pd.uid FROM product p"
						+ " inner join product_detail pd on pd.pkey=p.pkey" + " where p.pkey='" + arScope[1] + "'"
						+ " group by p.pkey";
				System.out.println("addCart()  " + query);
				List<Object[]> list = AbstractClass.listObj(query);
				for (int i = 0; i < list.size(); i++) {
					Object[] row = (Object[]) list.get(i);
					CartModel cm = new CartModel();
					for (int j = row.length - 1; j < row.length; j++) {

						cm.setUid(Integer.parseInt(row[j].toString()));
						cm.setVisible(Integer.parseInt(row[j - 1].toString()));
						cm.setStatus(Integer.parseInt(row[j - 2].toString()));
						cm.setDescription(row[j - 3].toString());
						cm.setSmrp(row[j - 4].toString());
						cm.setMrp(row[j - 5].toString());
						cm.setTitle(row[j - 6].toString());
						cm.setPkey(row[j - 7].toString());
					}
					return cm;
				}

			}
		} catch (Exception e) {
		}
		return null;

	}
	public static Object getOrderInfo(LoginSession ls, String oid, int part) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CartModel cm = null;
		Registration reg = null;
		Object obj = new Object();
		try {
			session.beginTransaction();
			if (part == 1) {
				if (ls.getAuthorize() == 1 | ls.getAuthorize() == 3)
					obj = session.createQuery("from OrderDetails where orderId='" + oid + "'").uniqueResult();
				else if (ls.getAuthorize() == 2)
					obj = session
							.createQuery("from OrderDetails where uid='" + ls.getId() + "' and orderId='" + oid + "'")
							.uniqueResult();
			} else if (part == 2) {
				if (ls.getAuthorize() == 1 | ls.getAuthorize() == 3)
					obj = session.createQuery("from ShippingModel where orderId='" + oid + "'").uniqueResult();
				else if (ls.getAuthorize() == 2)
					obj = session
							.createQuery("from ShippingModel where uid='" + ls.getId() + "' and orderId='" + oid + "'")
							.uniqueResult();
			} else if (part == 3) {

				String sql = "SELECT  id.gid,p.title,p.pkey,id.smrp,id.mrp,"
						/*+ " REPLACE(SUBSTRING(SUBSTRING_INDEX(pimg.url, ',', 1)," */
						/*+ "LENGTH(SUBSTRING_INDEX(pimg.url, ',',1-1)) + 1),',', '') as url" */
						+ " from item_details id"
						+ " inner join product p on p.pid=id.pid"
					/*--	+ " inner join  product_img pimg on pimg.ppid=id.ppid "----*/
						+ " inner join  product_detail pd on pd.pdid=pimg.pdid" + " where id.orderId='" + oid + "'";
				System.out.println(sql);

				List<Object[]> list = AbstractClass.listObj(sql);
				for (int i = 0; i < list.size(); i++) {
					Object[] row = (Object[]) list.get(i);
					cm = new CartModel(); // pi.url,p.title,pp.pkey,pp.mrp
					for (int j = row.length - 1; j < row.length; j++) {
						cm.setUrl(row[j].toString().replace("upload/", ""));
						cm.setMrp(row[j - 1].toString());
						cm.setSmrp(row[j - 2].toString());
						cm.setPkey(row[j - 3].toString());
						cm.setTitle(row[j - 4].toString());
						cm.setGid(row[j - 5].toString());
						System.out.println(cm);
					}
				}

				obj = cm;

			}

			else if (part == 4) {
				obj = session.createQuery("from ShippingModel where orderId='" + oid + "'").uniqueResult();

			} else if (part == 5) {
				obj = session.createQuery("from OrderDetails where orderId='" + oid + "'").uniqueResult();
			}
			if (part == 6) {
				String sql = "select r.mobile,r.state,r.pincode,r.city,r.address,r.street,r.name as count  from registration r"
						+ " inner join order_details od" + " on r.uid=od.uid where od.orderId='" + oid + "'";
				List<Object[]> list = AbstractClass.listObj(sql);
				for (int i = 0; i < list.size(); i++) {
					Object[] row = (Object[]) list.get(i);
					reg = new Registration();
					for (int j = row.length - 1; j < row.length; j++) {
						reg.setName(row[j].toString());
						reg.setStreet(row[j - 1].toString());
						reg.setAddress(row[j - 2].toString());
						reg.setCity(row[j - 3].toString());
						reg.setPincode(row[j - 4].toString());
						reg.setState(row[j - 5].toString());
						reg.setMobile(row[j - 6].toString());
					}
				}
				obj = reg;
			}

		} catch (Exception ex) {
		} finally {
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
		}
		return obj;
	}
	public static String getPayDetail(String json, double total) {
		String str = "";
		// String jsonString = "{ \"name\" : \"John\", \"age\" : \"20\",
		// \"address\" : \"some address\" }";
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			int shipping = jsonObject.getInt("charge.corrier");
			int free = jsonObject.getInt("charge.free");
			if (total > free)
				shipping = 0;

			str += "<tr><td></td><td></td><td class=\"text-right\">";
			str += "<p><strong>Subtotal: </strong></p><p>";
			str += "<strong>Shipping Charge: </strong>";
			str += "</p></td><td class=\"text-center\">";
			str += "<p><strong>&#8377;" + total + "</strong></p>";
			str += "<p><strong>&#8377;" + shipping + "</strong></p>";
			str += "</td></tr>";

			str += "<tr><td></td><td></td><td class=\"text-right\"><h4>";
			str += "<strong>Total: </strong></h4></td>";
			str += "<td class=\"text-center text-danger\"><h4>";
			str += "<strong>&#8377;" + (total + shipping) + "</strong>";
			str += "</h4></td></tr>";

			System.out.println(shipping + "  JSon Value ==" + free);
		} catch (Exception e) {
		}
		return str;
	}
public static List<CartModel> listCartModelByOrderNo(String oid,LoginSession ls) {
		
//		MerchantInfo mid = null;
		
		List<CartModel> lcm = null;
		String sql = "SELECT id.confirm,pp.ppid, id.gid,p.title,p.pkey,id.smrp,id.mrp,"
			/*	+ " REPLACE(SUBSTRING(SUBSTRING_INDEX(pimg.url, ',', 1),"  */
			/*	+ "LENGTH(SUBSTRING_INDEX(pimg.url, ',',1-1)) + 1),',', '') as url" + " from item_details id" */
				+ " inner join product p on p.pid=id.pid" +/* " inner join  product_img pimg on pimg.ppid=id.ppid " */
			/*	+ " inner join  product_detail pd on pd.pdid=pimg.pdid"*/
			/*	+ " inner join  product_price pp on pp.ppid=pimg.ppid" */
			
				 " where id.orderId='" + oid + "'";
//		if(!MerchantLogic.showMerchantInfo(ls.getId()).isEmpty()){
//			for (MerchantInfo mi : MerchantLogic.showMerchantInfo(ls.getId())) {
//				mid = mi;
//			}
//			sql+=" and id.company_id='" + mid.getId() + "'";
//			}
		System.out.println("listCartModelByOrderNo : " + sql);

		try {
			List<Object[]> list = AbstractClass.listObj(sql);
			for (int i = 0; i < list.size(); i++) {
				if (i == 0)
					lcm = new ArrayList<>();
				Object[] row = (Object[]) list.get(i);
				CartModel cm = new CartModel(); // pi.url,p.title,pp.pkey,pp.mrp
				for (int j = row.length - 1; j < row.length; j++) {
					String url="";
					url=row[j].toString().replace("upload/", "");
					System.out.println("----url-----  "+url);
					cm.setUrl(url);
					cm.setMrp(row[j - 1].toString());
					cm.setSmrp(row[j - 2].toString());
					cm.setPkey(row[j - 3].toString());
					cm.setTitle(row[j - 4].toString());
					cm.setGid(row[j - 5].toString());
					cm.setPpid(row[j - 6].toString());
					cm.setConfirm(Integer.parseInt(row[j - 7].toString()));
					lcm.add(cm);
				}
			}
		} catch (Exception e) {
		}
		return lcm;
	}
	public static boolean orderDetailSave(ShippingModel sm, String orderNum, HttpServletRequest request) {
		Session session = null;
		Transaction ts = null;
		SessionUser su = new SessionUser();
		boolean bol = false;
		int num = 0;
		try {
			if (su.getSession(request, "loginSession") | su.getSession(request, "pkey")) {
				session = HibernateUtil.getSessionFactory().openSession();
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				ts = session.beginTransaction();
				System.out.println("1----");
				if ((Integer) session.save(sm) > 0) {
					System.out.println("2----");
					double total = 0;
					HashMap<String, CartModel> hmap = (HashMap<String, CartModel>) request.getSession()
							.getAttribute("pkey");
					for (CartModel cm : CartSession.getCartData(hmap)) {
						System.out.println("CartModel----"+cm);
						total += Double.parseDouble(cm.getSmrp());
						total = total * cm.getQty();
						CartModel Item_detail = new CartModel();

						System.out.println("4----");

						Item_detail.setCompanyName("");
						Item_detail.setGid("");
						Item_detail.setMrp(cm.getMrp());
						Item_detail.setOrderId(orderNum);
						System.out.println("cm.getPid() " + cm.getPid() + " : " + cm.getPkey() + " : " + cm.getQty());
						Item_detail.setPid(cm.getPid());

						Item_detail.setPkey(cm.getPkey());
						Item_detail.setPmid(0);
						Item_detail.setQty(cm.getQty());
						Item_detail.setSmrp(cm.getSmrp());
						Item_detail.setUid(ls.getId());
						Item_detail.setPpid("");
						System.out.println("cm.getPpid(): " + cm.getPpid());
						Item_detail.setCompany_id(cm.getCompany_id());
						session.save(Item_detail);
						num++;
					}
					if (hmap.size() == num) {
						System.out.println("5----");
						/* payumoney require parameter */
						HashMap<String, String> hm = new HashMap<>();
						hm.put("charge.corrier", "");
						hm.put("charge.free", "");
						JSONObject json = new JSONObject();
						for (String key : PropertiesFile.readAttribute(hm).keySet()) {
							if (key.equals("charge.corrier"))
								json.put(key, hm.get(key));
							else if (key.equals("charge.free"))
								json.put(key, hm.get(key));
						}
						System.out.println("json file payumoney charge:-  " + json.toString());
						// JSONObject json2 = ItemBal.getShopCharge(total, 2);
						OrderDetails orDetails = new OrderDetails(orderNum, 0, ls.getId(), total, json.toString(),
								"");/*
									 * /---json2.toString());--another
									 * charge----
									 */
						
						
						/*  -1 orderId:- null getTitle:-product title test pkey:-null 
						 * name:-null mrp:-100 smrp:-99 size:-null color:-null count:-null url:-null pid:-0
						 *  
						 */
						if ((Integer) session.save(orDetails) > 0) {
							bol = true;
							ts.commit();
						}
					}
					if (bol) {
						request.getSession().removeAttribute("pkey");
						su.setSession(request, "order_no", orderNum);
					}
					// session.flush();

				}
			}

		} catch (Exception e) {
			if (ts != null)
				ts.rollback();

		} finally {
			session.close();
		}

		return bol;
	}

	public static HashMap<String, CartModel> getProductInfoList(HttpServletRequest request) {
		HashMap<String, CartModel> hmap = null;
		try {
			if (request.getSession().getAttribute("pkey") != null) {
				StringBuffer sb = new StringBuffer();
				hmap = (HashMap<String, CartModel>) request.getSession().getAttribute("pkey");
				sb.append(
						"select pd.count,pd.pdid,p.pkey,pd.color,pd.size,p.title,pd.mrp,pd.smrp,pd.description,pd.status,pd.visible,pd.uid FROM product p"
								+ " inner join product_detail pd on pd.pkey=p.pkey ");

				int num = 0;
				for (String key : hmap.keySet()) {
					// String[] key1 = getPkeyOrPPID(key);
					if (num == 0)
						sb.append(" where p.pkey='" + key + "'");
					else
						sb.append(" or p.pkey='" + key + "'");
					num++;
				}
				sb.append(" group by p.pkey");
				System.out.println("getProductInfoList query: " + sb.toString());

				if (hmap.size() > 0) {
					List<Object[]> list = AbstractClass.listObj(sb.toString());
					for (int i = 0; i < list.size(); i++) {
						Object[] row = (Object[]) list.get(i);
						for (int j = row.length - 1; j < row.length; j++) {
							String pkey_ppid = row[j - 9].toString();
							if (hmap.get(pkey_ppid) == null) {
								CartModel cm = new CartModel();
								cm.setUid(Integer.parseInt(row[j].toString()));
								cm.setVisible(Integer.parseInt(row[j - 1].toString()));
								cm.setStatus(Integer.parseInt(row[j - 2].toString()));
								cm.setDescription(row[j - 3].toString());
								cm.setSmrp(row[j - 4].toString());
								cm.setMrp(row[j - 5].toString());
								cm.setTitle(row[j - 6].toString());
								cm.setSize(row[j - 7].toString());
								cm.setColor(row[j - 8].toString());
								cm.setPkey(row[j - 9].toString());
								cm.setPpid(row[j - 10].toString());
								cm.setCount(row[j - 11].toString());
								hmap.put(row[j - 9].toString(), cm);
							} else {
								CartModel cm = hmap.get(pkey_ppid);
								cm.setMrp(row[j - 5].toString());
								cm.setSmrp(row[j - 4].toString());
								cm.setCount(row[j - 11].toString());
								hmap.put(pkey_ppid, cm);
							}
						}
						request.getSession().setAttribute("pkey", hmap);
					}
				}
			}
		} catch (Exception e) {
		}

		return hmap;
	}

	/*-- serparte key or ppid--*/
	private static String[] getPkeyOrPPID(String pkey) {
		String first = pkey.substring(0, 10); // gives "How ar"
		String second = pkey.substring(10, pkey.length()); // gives "e you?"
		return new String[] { first, second };
	}
}
