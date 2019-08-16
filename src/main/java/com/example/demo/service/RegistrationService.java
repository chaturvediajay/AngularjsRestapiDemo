package com.example.demo.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.IRegistrationDAO;
import com.example.demo.model.Registration;

@Service
public class RegistrationService implements IRegistrationService{
	@Autowired
	private IRegistrationDAO registrationDAO;

	@Override
	public List<Registration> getAllRegistrations() {
		return registrationDAO.getAllArticles();
	}

	@Override
	public Registration getArticleById(int registrationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addArticle(Registration registration) {
		 if (registrationDAO.articleExists(registration.getUsername(), registration.getName())) {
	            return false;
         } else {
        	 registrationDAO.addArticle(registration);
	            return true;
         }
	}

	@Override
	public void updateArticle(Registration registration) {
		registrationDAO.updateArticle(registration);
		
	}

	@Override
	public void deleteArticle(int registrationId) {
		registrationDAO.deleteArticle(registrationId);
		
	}
	
} 

