package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.IRegistrationDAO;
import com.example.demo.model.Registration;

@Service
public class RegistrationService implements IRegistrationService {
	@Autowired
	private IRegistrationDAO registrationDAO;

	@Override
	public List<Registration> getAllRegistrations() {
		return registrationDAO.getAllArticles();
	}

	@Override
	public Registration getRegistrationById(int registrationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createRegistration(Registration registration) {
		
			return registrationDAO.createRegistration(registration);
		
		
	}

	@Override
	public void updateRegistration(Registration registration) {
		registrationDAO.updateRegistration(registration);

	}

	@Override
	public void deleteRegistration(int registrationId) {
		registrationDAO.deleteRegistration(registrationId);

	}

}
