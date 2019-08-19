package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Registration;

public interface IRegistrationService {
	List<Registration> getAllRegistrations();

	Registration getRegistrationById(int registrationId);

	boolean createRegistration(Registration registration);

	void updateRegistration(Registration registration);

	void deleteRegistration(int registrationId);
	Registration registrationExists(String username,String pswd);
}
