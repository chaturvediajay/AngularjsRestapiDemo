package com.example.demo.ccavenue;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.cart.OrderInit;
import com.example.demo.cart.OrderTrasaction;
import com.example.demo.cart.PaymentDetails;
import com.example.demo.logic.CartLogic;
import com.example.demo.model.LoginSession;
import com.example.demo.model.ShippingModel;
import com.example.demo.scope.DateConvert;
import com.example.demo.scope.PropertiesFile;

/**
 * Servlet implementation class ccavenueRequest
 */
@WebServlet("/ccavenueRequest")
public class ccavenueRequest extends HttpServlet {
	private double totalAmt = 0;
	private static final long serialVersionUID = 1L;
//	private String merchantId = "130952";
//	private String accessCode = "AVYI70ED30BL87IYLB";
//	private String workingKey = "788561F55D60BFDD004E8D45300F65D9";
	
	private String merchantId = "";
	private String accessCode = "";
	private String workingKey = "";
	private String gatewayUrl="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ccavenueRequest() {
		super();
		HashMap<String, String> hm=new HashMap<>();
		hm.put("ccavenue.url", "");
		hm.put("ccavenue.merchantId", "");
		hm.put("ccavenue.accessCode", "");
		hm.put("ccavenue.workingKey", "");
		for (String key : PropertiesFile.readAttribute(hm).keySet()) {
			if(key.equals("ccavenue.url"))
				gatewayUrl=hm.get(key);
			else if(key.equals("ccavenue.merchantId"))
				merchantId=hm.get(key);
			else if(key.equals("ccavenue.accessCode"))
				accessCode=hm.get(key);
			else if(key.equals("ccavenue.workingKey"))
				workingKey=hm.get(key);	
		}	
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* trsaction detail send */
		String orderId = OrderInit.getOrderId();
		Random rand = new Random();

		// String trasactionId = Integer.toString(rand.nextInt()) +
		// (System.currentTimeMillis() / 1000L);
		// trasactionId = hashCal("SHA-256", trasactionId).substring(0, 20);
		// String trasactionId=Integer.toString(rand.nextInt()) +
		// (System.currentTimeMillis() / 1000);
		Long trasactionId = (long) (rand.nextDouble() * 10000000000L);
		String serverPort = (String.valueOf(request.getServerPort()) == "80") ? "" : ":" + request.getServerPort();
		PrintWriter writer = response.getWriter();

		if (getRequestSaveData(request, orderId, trasactionId)) {
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			ShippingModel sm = (ShippingModel) request.getSession().getAttribute("ship");
			String scheme = request.getScheme() + "://";
			String serverName = request.getServerName();
			String contextPath = request.getContextPath();
			String url = scheme + serverName + serverPort + contextPath;
			StringBuilder sb = new StringBuilder();
			sb.append("<html><head>");
			// sb.append("<script>window.onload = function() {var d = new
			// Date().getTime();document.getElementById(\"tid\").value =
			// d;};</script>");
			sb.append("<meta charset=\"utf-8\"><meta http-equiv=\"cache-control\" content=\"max-age=0\" />"
					+ "<meta http-equiv=\"cache-control\" content=\"no-cache\" />"
					+ "<meta http-equiv=\"expires\" content=\"0\" />"
					+ "<meta http-equiv=\"expires\" content=\"Tue, 01 Jan 1980 1:00:00 GMT\" />"
					+ "<meta http-equiv=\"pragma\" content=\"no-cache\" />");
			sb.append("</head>");
			sb.append("<body>****please wait your page redirect \n****"
					+ "<form method=\"post\"  id=\"customerData\" name=\"customerData\" action=\"ccavenuePayment\">");
			sb.append("<table width=\"40%\" height=\"100\" border='1' align=\"center\">");
			sb.append("<caption><font size=\"4\" color=\"blue\"><b>Integration Kit</b></font>");
			sb.append("</caption></table><table width=\"40%\" height=\"100\" border='1' align=\"center\">");
			sb.append(
					"<tr><td>Parameter Name:</td><td>Parameter Value:</td></tr><tr><td colspan=\"2\">Compulsory information</td>");
			sb.append("</tr><tr><td>TID :</td><td><input type=\"text\" name=\"tid\" id=\"tid\" value=\"" + trasactionId
					+ "\" /></td></tr>");
			sb.append(
					"<tr><td>Merchant Id</td><td><input type=\"text\" name=\"merchant_id\" id=\"merchant_id\" value=\""
							+ merchantId + "\" /></td>");
			sb.append("</tr><tr><td>Order Id</td><td><input type=\"text\" name=\"order_id\" value=\"" + orderId
					+ "\" /></td>");
			sb.append(
					"</tr><tr><td>Currency</td><td><input type=\"text\" name=\"currency\" value=\"INR\" /></td></tr><tr>");
			sb.append("<td>Amount</td><td><input type=\"text\" name=\"amount\" value=\"" + getTotalAmt()
					+ "\" /></td></tr>");
			sb.append("<tr><td>Redirect URL</td><td><input type=\"text\" name=\"redirect_url\" value=\"" + url
					+ "/reciept" + "\" />");
			sb.append("</td></tr><tr><td>Cancel URL</td><td><input type=\"text\" name=\"cancel_url\" ");
			sb.append("value=\"" + url + "/cancel" + "\" /></td></tr>");
			sb.append(
					"<tr><td>Language</td><td><input type=\"text\" name=\"language\" id=\"language\" value=\"EN\" /></td>");
			sb.append("</tr><tr><td colspan=\"2\">Billing information(optional):</td></tr><tr><td>Billing Name</td>"
					+ "<td><input type=\"text\" name=\"billing_name\" value=\"" + ls.getName() + "\" /></td></tr>");

			sb.append("<tr><td>Billing Address:</td><td><input type=\"text\" name=\"billing_address\" value=\""
					+ sm.getsAddress1()+"  "+sm.getsAddress2() + "\" /></td></tr>"
					+ "<tr><td>Billing City:</td><td><input type=\"text\" name=\"billing_city\" value=\""
					+ sm.getsCity() + "\" /></td></tr>");

			sb.append("<tr><td>Billing State:</td><td><input type=\"text\" name=\"billing_state\" value=\""
					+ sm.getsSate() + "\" /></td></tr>"
					+ "<tr><td>Billing City:</td><td><input type=\"text\" name=\"billing_city\" value=\""
					+ sm.getsCity() + "\" /></td></tr>");

			sb.append("<tr><td>Billing Zip:</td><td><input type=\"text\" name=\"billing_zip\" value=\""
					+ sm.getsPincode() + "\" /></td></tr>");

			sb.append(
					"<tr><td>Billing Country:</td><td><input type=\"text\" name=\"billing_country\" value=\"India\" /></td></tr>"
							+ "<tr><td>Billing Tel:</td><td><input type=\"text\" name=\"billing_tel\" value=\""
							+ sm.getsMobile() + "\" /></td></tr>");

			sb.append("	<tr><td>Billing Email:</td><td><input type=\"text\" name=\"billing_email\" value=\""
					+ ls.getEmail() + "\" /></td></tr>"
					+ "<tr><td colspan=\"2\">Shipping information(optional):</td></tr>");

			sb.append("<tr><td>Shipping Name</td><td><input type=\"text\" name=\"delivery_name\" value=\""
					+ sm.getsName() + "\" /></td></tr>"
					+ "<tr><td>Shipping Address:</td><td><input type=\"text\" name=\"delivery_address\" value=\""
					+ sm.getsAddress1()+"  "+sm.getsAddress2() + "\" /></td></tr>");

			sb.append("<tr><td>Shipping City:</td><td><input type=\"text\" name=\"delivery_city\" value=\""
					+ sm.getsCity() + "\" /></td></tr>"
					+ "	<tr><td>Shipping State:</td><td><input type=\"text\" name=\"delivery_state\" value=\""
					+ sm.getsPincode() + "\" /></td></tr>");

			sb.append("<tr><td>Shipping Zip:</td><td><input type=\"text\" name=\"delivery_zip\" value=\""
					+ sm.getsPincode() + "\"/></td></tr>"
					+ "<tr><td>Shipping Country:</td><td><input type=\"text\" name=\"delivery_country\" value=\"India\" /></td></tr>");

			sb.append("<tr><td>Shipping Tel:</td><td><input type=\"text\" name=\"delivery_tel\" value=\""
					+ sm.getsMobile() + "\" /></td></tr>"
					+ "<tr><td>Merchant Param1</td><td><input type=\"text\" name=\"merchant_param1\" value=\""+trasactionId+"\" /></td></tr>");

			sb.append(
					"<tr><td>Merchant Param2</td><td><input type=\"text\" name=\"merchant_param2\" value=\"ccavenue\" /></td></tr>"
							+ "	<tr><td>Merchant Param3</td><td><input type=\"text\" name=\"merchant_param3\" value=\"additional Info.\" /></td></tr>");

			sb.append(
					"<tr><td>Merchant Param4</td><td><input type=\"text\" name=\"merchant_param4\" value=\"additional Info.\" /></td></tr>"
							+ "<tr><td>Merchant Param5</td><td><input type=\"text\" name=\"merchant_param5\" value=\"additional Info.\" /></td></tr>");

			sb.append("<tr><td>Promo Code:</td><td><input type=\"text\" name=\"promo_code\" value=\"\"/></td></tr>"
					+ "<tr><td>Customer Id:</td><td><input type=\"text\" name=\"customer_identifier\" value=\"\"/></td></tr>");

			sb.append("<tr><td></td><td><INPUT TYPE=\"submit\" value=\"Checkout\"></td></tr>"
					+ "</table></form>"
					+ "<script>"
					+ " document.getElementById(\"customerData\").submit(); " + "</script>"
					+ "</body></html>");

			writer.println(sb.toString());
		}
	}

	private boolean getRequestSaveData(HttpServletRequest request, String orderId, Long trsactionId) {
		boolean bol = false;
		if (orderId != null & trsactionId != null) {
			setTotalAmt(OrderInit.totalOrderAmt(request));
			LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
			ShippingModel sm = (ShippingModel) request.getSession().getAttribute("ship");
			sm.setOrderId(orderId);
			sm.setUid(ls.getId());
			bol = CartLogic.orderDetailSave(sm, orderId, request);
			PaymentDetails pd = new PaymentDetails();
			pd.setTxid(Long.toString(trsactionId));
			pd.setOrderId(orderId);
			pd.setDatetime(DateConvert.nowTimeGMT());
			bol = OrderTrasaction.saveOrder(pd);
		}
		return bol;

	}

	public String hashCal(String type, String str) {
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		return hexString.toString();
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println((System.currentTimeMillis() / 100000000) + Integer.toString(rand.nextInt()));
		System.out.println((long) (rand.nextDouble() * 10000000000L));
	}
}
