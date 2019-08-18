package com.example.demo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IRegistrationDAO;
import com.example.demo.model.Registration;
import com.example.demo.scope.SHASecure;

@Transactional
@Repository
public class RegistrationDAO implements IRegistrationDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Registration getRegistrationById(int registrationId) {
		return entityManager.find(Registration.class, registrationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registration> getAllArticles() {
		String hql = "FROM Registration as atcl ORDER BY atcl.uid";
		return (List<Registration>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public boolean createRegistration(Registration registration) {

		try {
			registration.setPswd(SHASecure.get_SHA_1_SecurePassword(registration.getPswd()));
			entityManager.persist(registration);
			
			System.out.println("registration : "+registration.toString().length()+" : "+registration);

			if (registration.getId() > 0)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public void updateRegistration(Registration registration) {
		Registration artcl = getRegistrationById(registration.getId());
		artcl.setName(registration.getName());
		artcl.setUsername(registration.getUsername());
		entityManager.flush();
	}

	@Override
	public void deleteRegistration(int registrationId) {
		entityManager.remove(getRegistrationById(registrationId));
	}

	@Override
	public boolean registrationExists(String title, String category) {
		String hql = "FROM Registration as atcl WHERE atcl.title = ? and atcl.category = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category).getResultList()
				.size();
		return count > 0 ? true : false;
	}
}
