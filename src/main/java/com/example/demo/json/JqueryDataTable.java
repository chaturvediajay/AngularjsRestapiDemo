package com.example.demo.json;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JqueryDataTable {
	/* 16 */ private int start = 0;
	/* 17 */ private int length = 0;
	/* 18 */ private int draw = 0;
	/* 19 */ private String order_column = "";
	/* 20 */ private String order_dir = "";
	/* 21 */ private String search_value = "";
	/* 22 */ private String pkey = "";

	public void getRequestData(HttpServletRequest request) {
		try {
			/* 28 */ Enumeration<String> kayParams = request.getParameterNames();
			/* 29 */ while (kayParams.hasMoreElements()) {
				/* 30 */ String key = (String) kayParams.nextElement();
				/* 31 */ String value = request.getParameter(key).toString();
				/* 32 */ if (key.equals("start")) {
					/* 33 */ this.start = Integer.parseInt(value);
					continue;
					/* 34 */ }
				if (key.equals("length")) {
					/* 35 */ this.length = Integer.parseInt(value);
					continue;
					/* 36 */ }
				if (key.equals("draw")) {
					/* 37 */ this.draw = Integer.parseInt(value);
					continue;
					/* 38 */ }
				if (key.equals("order[0][column]")) {
					/* 39 */ this.order_column = value;
					continue;
					/* 40 */ }
				if (key.equals("order[0][dir]")) {
					/* 41 */ this.order_dir = value;
					continue;
					/* 42 */ }
				if (key.equals("search[value]")) {
					/* 43 */ this.search_value = value;
					continue;
					/* 44 */ }
				if (key.equals("pkey")) {
					/* 45 */ this.pkey = value;
				}
			}
			/* 48 */ } catch (Exception e) {
		}
	}

	public void getResposeJson(HttpServletResponse response, int result_count, String json) {
		try {
			/* 56 */ JsonObject jsonResponse = new JsonObject();
			/* 57 */ jsonResponse.addProperty("draw", Integer.valueOf(this.draw));
			/* 58 */ jsonResponse.addProperty("recordsTotal", Integer.valueOf(result_count));
			/* 59 */ jsonResponse.addProperty("recordsFiltered", Integer.valueOf(result_count));
			/* 60 */ jsonResponse.addProperty("data", json);
			/* 62 */ response.setContentType("application/Json");
			/* 63 */ response.getWriter().print(jsonResponse.toString());
			/* 64 */ } catch (NumberFormatException e) {
		}
		/* 67 */ catch (IOException e) {
		}
	}

	/* 74 */ public int getStart() {
		return this.start;
	}

	/* 79 */ public void setStart(int start) {
		this.start = start;
	}

	/* 84 */ public int getLength() {
		return this.length;
	}

	/* 89 */ public void setLength(int length) {
		this.length = length;
	}

	/* 94 */ public int getDraw() {
		return this.draw;
	}

	/* 99 */ public void setDraw(int draw) {
		this.draw = draw;
	}

	/* 104 */ public String getOrder_column() {
		return this.order_column;
	}

	/* 109 */ public void setOrder_column(String order_column) {
		this.order_column = order_column;
	}

	/* 114 */ public String getOrder_dir() {
		return this.order_dir;
	}

	/* 119 */ public void setOrder_dir(String order_dir) {
		this.order_dir = order_dir;
	}

	/* 124 */ public String getSearch_value() {
		return this.search_value;
	}

	/* 129 */ public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	/* 134 */ public String getPkey() {
		return this.pkey;
	}

	/* 139 */ public void setPkey(String pkey) {
		this.pkey = pkey;
	}
}
