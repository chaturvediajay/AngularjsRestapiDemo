package com.example.demo.json;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.scope.SessionUser;

public class UploadFileCate {
	private SessionUser su = new SessionUser();

	public ArrayList<String> addUrlImage(HttpServletRequest request, String url, String cate) {
		HttpSession session = request.getSession();
		ArrayList<String> arrlist = null;
		if (cate.equals("multiple")) {
			if (session.getAttribute("img") != null) {
				arrlist = this.su.getSessionValueArray(session, "img");
				arrlist.add(url);

				session.setAttribute("img", arrlist);
				return arrlist;
			}
			arrlist = new ArrayList<String>();
			arrlist.add(url);
			session.setAttribute("img", arrlist);
			return arrlist;
		}
		if (cate.equals("single")) {
			arrlist = new ArrayList<String>();
			System.out.println("urlurlurlurl   " + url);
			arrlist.add(url);
			session.setAttribute("img", arrlist);
			return arrlist;
		}

		return arrlist;
	}
}