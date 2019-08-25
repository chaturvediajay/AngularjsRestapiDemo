package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.util.MultiValueMap;

import com.example.demo.model.Product;

public interface IProductService {

	Product getProductById(int productId);

	Product createProduct(MultiValueMap body, HttpServletRequest request);

	Product updateProduct(Product product);

	List<?> retriveProductJson(HttpServletRequest request, int start, int length);

	Product productExists(String username, String pswd);
}
