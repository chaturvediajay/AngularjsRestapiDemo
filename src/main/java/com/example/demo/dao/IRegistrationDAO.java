package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Registration;

public interface IRegistrationDAO {
	List<Registration> getAllArticles();

	Registration getArticleById(int registrationId);

	void addArticle(Registration registration);

	void updateArticle(Registration registration);

	void deleteArticle(int registrationId);

	boolean articleExists(String title, String category);
}
