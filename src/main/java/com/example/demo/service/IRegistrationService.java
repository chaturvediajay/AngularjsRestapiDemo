package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Registration;

public interface IRegistrationService {
	List<Registration> getAllRegistrations();

	Registration getArticleById(int registrationId);

	boolean addArticle(Registration registration);

	void updateArticle(Registration registration);

	void deleteArticle(int registrationId);
}
