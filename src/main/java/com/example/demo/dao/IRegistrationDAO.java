package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Registration;

public interface IRegistrationDAO {
	List<Registration> getAllArticles();

	Registration getRegistrationById(int registrationId);

	boolean createRegistration(Registration registration);

	void updateRegistration(Registration registration);

	void deleteRegistration(int registrationId);

	Registration registrationExists(String username, String password);
}
