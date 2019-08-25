package com.example.demo.service;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import com.example.demo.model.LoginSession;
import com.example.demo.model.Menu1;
import com.example.demo.model.Product;
import com.example.demo.model.Registration;
import com.example.demo.scope.SessionUser;
import com.example.demo.scope.StrongAES;

@Transactional
@Repository
public class ProductService implements IProductService {
	@PersistenceContext
	private EntityManager entityManager;
	private int count = 0;

	@Override
	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product createProduct(MultiValueMap body, HttpServletRequest request) {
		Product product = new Product();

		try {
			Registration re = (Registration) request.getSession().getAttribute("user");
			product.setM1Id(Integer.parseInt(body.getFirst("optMenu1").toString()));
			product.setM2Id(Integer.parseInt(body.getFirst("optMenu2").toString()));
			product.setM3Id(Integer.parseInt(body.getFirst("optMenu3").toString()));
			product.setTitle(body.getFirst("txtTitle").toString());
			product.setPkey(StrongAES.stringIntGen(10, 3));
			product.setUid(re.getUid());
			System.out.println(product);
			entityManager.persist(product);
		} catch (Exception e) {

		}

		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> retriveProductJson(HttpServletRequest request, int start, int length) {
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

//			Query q  = entityManager.createNativeQuery(query[0] );

			BigInteger rowCnt = (BigInteger) entityManager.createNativeQuery("SELECT count(1) FROM product").getSingleResult();

			System.out.println("***rowcount " + rowCnt);
//			Query hSql  = entityManager.createQuery("FROM Product");
//			hSql.setFirstResult(start);
//			hSql.setMaxResults(length);
//			list = hSql.getResultList();
			
			
			list=entityManager.createQuery(
				    "FROM Product")
				    .setFirstResult(start)
				    .setMaxResults(length)
				    .getResultList();
			for(Product p:(List<Product>)list) {
				System.out.println(p);
			}
			
			


			System.out.println("******** size " + list.size());

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
			System.out.println(e.toString());

		}
		return list;
	}

	@Override
	public Product productExists(String username, String pswd) {

		return null;
	}

}
