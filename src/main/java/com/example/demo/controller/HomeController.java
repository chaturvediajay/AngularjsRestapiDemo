package com.example.demo.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.TrasactionModel;
import com.example.demo.ccavenue.ccavenuePayment;
import com.example.demo.logic.CartLogic;
import com.example.demo.logic.ProductLogic;
import com.example.demo.logic.RegLogic;
import com.example.demo.model.LoginSession;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.Registration;
import com.example.demo.model.ShippingModel;
import com.example.demo.scope.CartSession;
import com.example.demo.scope.SHASecure;
import com.example.demo.scope.SessionUser;
import com.example.demo.service.IRegistrationService;
import com.google.gson.JsonObject;

import antlr.collections.List;

@Controller
@ComponentScan(basePackages = { "com.example.demo" })
public class HomeController {

	@Autowired
	private IRegistrationService registrationService;

	@GetMapping(value = "/")
	public String getIndex() {
		return "index";
	}

	@RequestMapping(value = { "/products" }, method = { RequestMethod.GET })
	public String getProduct(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "products";
	}

	@RequestMapping(value = { "/single" }, method = { RequestMethod.GET })
	public String getSingle(HttpServletRequest request, ModelMap map) {
		try {
			String pkey = request.getParameter("pkey");
			System.out.println("pkey:-  " + pkey);
			if (pkey != null)
				map.addAttribute("product_desc", ProductLogic.getProductHome("single", pkey));
			else
				return "index";
		} catch (Exception e) {
		}
		return "single";
	}

	@RequestMapping(value = { "/checkout" }, method = { RequestMethod.GET })
	public String getCheckout(HttpServletRequest request, ModelMap map) {
		try {
			map.addAttribute("retrive", CartSession.getCartSession(request));
		} catch (Exception e) {
		}
		return "checkout";
	}

	@RequestMapping(value = { "/contactUs" }, method = { RequestMethod.GET })
	public String getContactUs(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "contactUs";
	}

	@RequestMapping(value = { "/shipping" }, method = { RequestMethod.GET })
	public String getShipping(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "redirect:login?shop=payment";

		} catch (Exception e) {
		}
		return "shipping";
	}

	@RequestMapping(value = { "/shipping" }, method = { RequestMethod.POST })
	public String postShipping(HttpServletRequest request, ModelMap map) {
		try {
			ShippingModel sm = new ShippingModel(request);
			if (sm.checkValid().size() == 0) {
				sm.setSm(sm);
				sm.sessionShipSave(request, true);
			} else {
				map.addAttribute("error", sm.checkValid());
				return "shipping";
			}
		} catch (Exception e) {
		}
		return "redirect:payment";
	}

	@RequestMapping(value = { "/aboutUs" }, method = { RequestMethod.GET })
	public String getAboutUs(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {

		}
		return "aboutUs";
	}

	@RequestMapping(value = { "/gallery" }, method = { RequestMethod.GET })
	public String getGallery(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "gallery";
	}

	@RequestMapping(value = { "/terms" }, method = { RequestMethod.GET })
	public String getTerms(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "terms";
	}

	@RequestMapping(value = { "/testimonial" }, method = { RequestMethod.GET })
	public String getTestimonial(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "testimonial";
	}

	@RequestMapping(value = { "/joinUs" }, method = { RequestMethod.GET })
	public String getJoinUs(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "joinUs";
	}

	@RequestMapping(value = { "/signUp" }, method = { RequestMethod.GET })
	public String getSignUp(HttpServletRequest request, ModelMap map) {
		try {
//			if (RegLogic.checkLogin(request))
//				return "redirect:admin/";

		} catch (Exception e) {

		}
		return "signUp";
	}

	@PostMapping("signUp")
	public String postSignUp(@RequestParam(name = "username") String username, @RequestParam MultiValueMap body,
			ModelMap map) {
		try {
			Registration registration = new Registration();
			registration.setName(body.getFirst("name").toString());
			registration.setUsername(body.getFirst("username").toString());
			registration.setEmail(body.getFirst("email").toString());
			registration.setPswd(body.getFirst("pswd").toString());
			registration.setMobile(body.getFirst("mobile").toString());
//			System.out.println(registrationService.createRegistration(registration));
//			
			if (registrationService.createRegistration(registration))
				map.addAttribute("error", "account create sucessfully.");
			else
				map.addAttribute("error", "try later.");

		} catch (Exception e) {
		}
		return "signUp";
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String getLogin(HttpServletRequest request, ModelMap map) {
		try {
			if (RegLogic.checkLogin(request))
				return "login";
		} catch (Exception e) {
		}
		return "redirect:admin/";
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
	public String postLogin(HttpServletRequest request, @RequestParam MultiValueMap body, ModelMap map) {
		try {
			String username = body.getFirst("username").toString();
			String pswd = body.getFirst("pswd").toString();
			Registration reg = registrationService.registrationExists(username, pswd);
			if (reg != null) {
				request.getSession().setAttribute("user", reg);
				return "redirect:admin/";
			} else
				map.addAttribute("error", "try later.");

		} catch (Exception e) {
		}
		return "login";
	}

	@RequestMapping(value = { "/adminLogin" }, method = { RequestMethod.GET })
	public String getAdminLogin(HttpServletRequest request, ModelMap map) {
		try {

		} catch (Exception e) {
		}
		return "adminLogin";
	}

	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public String getlogout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@RequestMapping(value = { "/payment" }, method = { RequestMethod.GET })
	public String getpayment(HttpServletRequest request, ModelMap map) {
		if (RegLogic.checkLogin(request))
			return "redirect:login";
		else {
			map.addAttribute("retrive", CartSession.getCartSession(request));
			return "payment";
		}

	}

	@RequestMapping(value = { "/reciept" }, method = { RequestMethod.GET })
	public String getReceipt(HttpServletRequest request, ModelMap model) {
		try {
			SessionUser su = new SessionUser();
			if (su.getSession(request, "order_no") & su.getSession(request, "loginSession")) {
				LoginSession ls = (LoginSession) request.getSession().getAttribute("loginSession");
				String order_no = (String) request.getSession().getAttribute("order_no");
				model.addAttribute("order_no", order_no);
				model.addAttribute("billingAdd", (Registration) RegLogic.getProductUserDetail(ls.getId()));
				OrderDetails od = (OrderDetails) CartLogic.getOrderInfo(ls, order_no, 1);
				model.addAttribute("orderDetail", od);
				model.addAttribute("other", CartLogic.getPayDetail(od.getOther(), od.getTotal()));
				model.addAttribute("shippingAdd", (ShippingModel) CartLogic.getOrderInfo(ls, order_no, 2));
				model.addAttribute("itemDetail", (List) CartLogic.listCartModelByOrderNo(order_no, ls));
				request.getSession().removeAttribute("order_no");
			} else
				model.addAttribute("order_no", "No order found");

		} catch (Exception ex) {
			model.addAttribute("msg", "error:- " + ex.toString());
		}
		return "reciept";
	}

	@RequestMapping(value = { "/reciept" }, method = { RequestMethod.POST })
	public String postReceipt(HttpServletRequest request, ModelMap map) {
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			String result = "";
			String key = "";
			if (request.getParameter("encResp") != null | request.getParameter("encResp").length() > 0)
				ccavenuePayment.getResponse(request);
			else
				System.out.println(" /receipt  " + TrasactionModel.saveData(request));

			String serverName = request.getServerName();
			int portNumber = request.getServerPort();
			String contextPath = request.getContextPath();

			System.out.println(" ^^^^^^^  " + serverName + ":" + portNumber + contextPath);
		} catch (Exception e) {
		}
		return "redirect:reciept";
	}

	@RequestMapping(value = { "/cancel" }, method = { RequestMethod.POST })
	public String postCancel(HttpServletRequest request, Model model) {
		try {
			Enumeration<String> kayParams = request.getParameterNames();
			while (kayParams.hasMoreElements()) {
				String key = (String) kayParams.nextElement();
				// String result = key + "=" + request.getParameter(key) +
				// (kayParams.hasMoreElements() ? "," : "") + "\n";
				String val = request.getParameter(key);
				if (key.equals("tracking_id") & val.length() > 0) {
					JsonObject jobj = TrasactionModel.saveData(request);
					if (jobj != null) {
						jobj = ccavenuePayment.getResponse(request);
						model.addAttribute("firstname", jobj.get("firstname"));
						model.addAttribute("lastname", jobj.get("lastname"));
						model.addAttribute("time", jobj.get("trans_date"));
						model.addAttribute("error", jobj.get("order_status"));
						model.addAttribute("payid", jobj.get("tracking_id"));
						model.addAttribute("amount", jobj.get("mer_amount"));
						return "cancel";
					}
				} else if (key.equals("mihpayid") & val.length() > 0) {
					JsonObject jobj = TrasactionModel.saveData(request);
					if (jobj != null) {
						model.addAttribute("firstname", jobj.get("firstname"));
						model.addAttribute("lastname", jobj.get("lastname"));
						model.addAttribute("time", jobj.get("addedon"));
						model.addAttribute("error", jobj.get("error_Message"));
						model.addAttribute("payid", jobj.get("payuMoneyId"));
						model.addAttribute("amount", jobj.get("amount"));
						return "cancel";
					}
				}

			}
		} catch (Exception e) {
		}
		return "redirect:/";
	}

	@RequestMapping(value = { "/cancel" }, method = { RequestMethod.GET })
	public String getCancel(@ModelAttribute("firstname") String firstname, @ModelAttribute("lastname") String lastname,
			@ModelAttribute("time") String time, @ModelAttribute("error") String error,
			@ModelAttribute("payid") String payid, @ModelAttribute("amount") String amount, ModelMap model) {
		try {
			if (firstname.length() > 0 & lastname.length() > 0 & time.length() > 0 & payid.length() > 0) {
				model.addAttribute("firstname", firstname.replaceAll("\\+", ""));
				model.addAttribute("lastname", lastname.replaceAll("\\+", ""));
				model.addAttribute("time", time.replaceAll("\\+", ""));
				model.addAttribute("error", error.replaceAll("\\+", ""));
				model.addAttribute("payid", payid.replaceAll("\\+", ""));
				model.addAttribute("amount", amount.replaceAll("\\+", ""));
				return "cancel";
			}

		} catch (Exception e) {
		}

		return "redirect:/";
	}

}